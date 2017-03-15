package database.manager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import database.DBManager;
import database.MissingTableAnnotation;
import database.annotation.Column;
import database.annotation.Id;
import database.annotation.Table;

public class ObjectManager<T> {

    private Connection conn;
    private Class<T> c;
    protected String tableName;
    private Map<String, String> columnNames;

    public ObjectManager(Class<T> className) {
        try {
            conn = DBManager.getInstance().getConnection();
            c = className;
            Table tableAnnot = c.getAnnotation(Table.class);
            if (tableAnnot == null) { throw new MissingTableAnnotation(c); }
            tableName = tableAnnot.name();

            /* Parse field column name */
            columnNames = new HashMap<String, String>();
            for (Field f : c.getDeclaredFields()) {
                Column c = f.getAnnotation(Column.class);
                if (c != null) {
                    columnNames.put(f.getName(), c.name());
                }
            }
        } catch (SQLException | MissingTableAnnotation e) {
            e.printStackTrace();
        }
    }

    public T find(int id) {
        T entity = null;
        try {
            String sql = "SELECT * FROM `" + tableName + "` WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                entity = (T) c.newInstance();
                for (String property : columnNames.keySet()) {
                    String columnName = columnNames.get(property);
                    if (columnName != null) {
                        Method setter = c.getMethod(setterOf(property),
                                c.getDeclaredField(property).getType());
                        setter.invoke(entity, rs.getObject(columnName));
                    }
                }
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    public List<T> findAll() {
        List<T> entities = new ArrayList<T>();
        try {
            String sql = "SELECT * FROM `" + tableName + "`";
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                T entity = (T) c.newInstance();
                for (String property : columnNames.keySet()) {
                    String columnName = columnNames.get(property);
                    if (columnName != null) {
                        Method setter = c.getMethod(setterOf(property),
                                c.getDeclaredField(property).getType());
                        setter.invoke(entity, rs.getObject(columnName));
                    }
                }
                entities.add(entity);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }

    /**
     * 
     * @param prop The name of the field
     * @param value The value
     * @return A list of matching objects
     */
    public List<T> findBy(String prop, Object value) {
        List<T> entities = new ArrayList<T>();
        try {
            String sql = "SELECT * FROM `" + tableName + "` WHERE " + columnNames.get(prop)
                    + " = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, value);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                T entity = (T) c.newInstance();
                for (String property : columnNames.keySet()) {
                    String columnName = columnNames.get(property);
                    if (columnName != null) {
                        Method setter = c.getMethod(setterOf(property),
                                c.getDeclaredField(property).getType());
                        setter.invoke(entity, rs.getObject(columnName));
                    }
                }
                entities.add(entity);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }

    public T findOneBy(String prop, Object value) {
        List<T> entities = this.findBy(prop, value);
        if (entities.isEmpty()) return null;
        return entities.get(0);
    }

    public boolean create(T entity) {
        if(entity == null) return false;
        try {
            Set<String> properties = columnNames.keySet();
            String columnNames[] = new String[properties.size() - 1]; // -1 to remove @Id
            String parameters[] = new String[properties.size() - 1]; // -1 to remove @Id
            Object values[] = new Object[properties.size() - 1]; // -1 to remove @Id

            int i = 0;
            for (Field f : c.getDeclaredFields()) {
                if (f.getAnnotation(Id.class) == null && f.getAnnotation(Column.class) != null) {
                    columnNames[i] = f.getAnnotation(Column.class).name();
                    parameters[i] = "?";
                    Method getter = c.getMethod(getterOf(f));
                    values[i] = getter.invoke(entity);
                    i++;
                }
            }

            String sql = "INSERT INTO `" + tableName + "` (" + String.join(", ", columnNames)
                    + ") VALUES (" + String.join(", ", parameters) + ")";

            PreparedStatement ps = conn.prepareStatement(sql);
            for (i = 1; i <= values.length; i++) {
                ps.setObject(i, values[i - 1]);
            }
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(T entity) {
        if(entity == null) return false;
        try {
            Set<String> properties = columnNames.keySet();
            String sql[] = new String[properties.size() - 1]; // -1 to remove @Id
            Object values[] = new Object[properties.size() - 1]; // -1 to remove @Id

            int i = 0;
            for (Field f : c.getDeclaredFields()) {
                if (f.getAnnotation(Id.class) == null && f.getAnnotation(Column.class) != null) {
                    Method getter = c.getMethod(getterOf(f));
                    values[i] = getter.invoke(entity);
                    sql[i] = f.getAnnotation(Column.class).name() + " = ?";
                    i++;
                }
            }

            String query = "UPDATE `" + tableName + "` SET " + String.join(",", sql)
                    + " WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            for (i = 1; i <= values.length; i++) {
                ps.setObject(i, values[i - 1]);
            }
            
            Field idF = c.getDeclaredField("id");
            String mName = getterOf(idF);
            Method m = c.getMethod(mName);
            Object o = m.invoke(entity);
            ps.setObject(values.length + 1, o);
            System.out.println("ObjectManager.update() > "+ps);
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(T entity) {
        if(entity == null) return false;
        try {
            String sql = "DELETE FROM `" + tableName + "` WHERE id=?";

            Method getId = c.getMethod("getId");
            int id = (int) getId.invoke(entity);

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    protected String getterOf(Field f) {
        return "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
    }

    protected String setterOf(String property) {
        return "set" + property.substring(0, 1).toUpperCase() + property.substring(1);
    }
}
