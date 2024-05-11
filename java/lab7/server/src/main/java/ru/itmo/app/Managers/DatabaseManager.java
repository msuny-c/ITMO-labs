package ru.itmo.app.Managers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itmo.app.Interfaces.IDataBaseProcessor;
import ru.itmo.app.Models.*;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Stack;

public class DatabaseManager {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);
    private final Connection connection;
    private final int UNDEFINED = 0;

    public DatabaseManager(String user, String pass) throws SQLException {
        Properties auth = new Properties();
        auth.put("user", user);
        auth.put("password", pass);
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studs", auth);
    }

    public int add(HumanBeing object, String user) throws SQLException {
        int coordinates_id, car_id, human_id;
        coordinates_id = car_id = human_id = UNDEFINED;
        String queryAddCoords = "INSERT INTO coordinates (id, x, y) VALUES(default, ?, ?) RETURNING id";
        PreparedStatement coordsStatement = connection.prepareStatement(queryAddCoords);
        coordsStatement.setFloat(1, object.coordinates().getX());
        coordsStatement.setLong(2, object.coordinates().getY());
        ResultSet coordsResultSet = coordsStatement.executeQuery();
        if (coordsResultSet.next()) {
            coordinates_id = coordsResultSet.getInt("id");
        }

        // Добавляем авто
        String carAddQuery = "INSERT INTO car (id, name, cool) VALUES(default, ?, ?) RETURNING id";
        PreparedStatement carStatement = connection.prepareStatement(carAddQuery);
        carStatement.setString(1, object.car().getName());
        carStatement.setBoolean(2, object.car().isCool());
        ResultSet carResultSet = carStatement.executeQuery();
        if (carResultSet.next()) {
            car_id = carResultSet.getInt("id");
        }

        // Добавляем человека
        String humanAddQuery = "INSERT INTO human_being (id, name, coordinates_id, mood, weapon_type, car_id, realHero, hasToothPick, impactSpeed, user_name) " +
                "VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
        PreparedStatement humanStatement = connection.prepareStatement(humanAddQuery);
        humanStatement.setString(1, object.name());
        humanStatement.setInt(2, coordinates_id);
        humanStatement.setBoolean(6, object.realHero());
        if (object.mood() != null) {
            humanStatement.setString(3, object.mood().toString());
        } else {
            humanStatement.setNull(3, Types.VARCHAR);
        }
        if (object.weaponType() != null) {
            humanStatement.setString(4, object.weaponType().toString());
        } else {
            humanStatement.setNull(4, Types.VARCHAR);
        }
        if (object.hasToothPick() != null) {
            humanStatement.setBoolean(7, object.hasToothPick());
        } else {
            humanStatement.setNull(7, Types.BOOLEAN);
        }
        if (object.impactSpeed() != null) {
            humanStatement.setLong(8, object.impactSpeed());
        } else {
            humanStatement.setNull(8, Types.BIGINT);
        }
        humanStatement.setString(9, user);

        if (car_id != UNDEFINED) {
            humanStatement.setInt(5, car_id);
        } else {
            humanStatement.setNull(5, Types.INTEGER);
        }
        ResultSet humanResultSet = humanStatement.executeQuery();
        if (humanResultSet.next()) {
            human_id = humanResultSet.getInt("id");
        }

        return human_id;
    }

    public void update(Integer id, HumanBeing object) throws SQLException {
        int coordinates_id, car_id;
        coordinates_id = car_id = UNDEFINED;
        String update_human = "UPDATE human_being SET name = ?, mood = ?, weapon_type = ?, realHero = ?, hasToothPick = ?, impactSpeed = ? WHERE id = ? RETURNING coordinates_id, car_id";
        PreparedStatement human_statement = connection.prepareStatement(update_human);
        human_statement.setString(1, object.name());
        if (object.mood() == null) {
            human_statement.setNull(2, Types.VARCHAR);
        } else {
            human_statement.setString(2, object.mood().toString());
        }
        if (object.weaponType() == null) {
            human_statement.setNull(3, Types.VARCHAR);
        } else {
            human_statement.setString(3, object.weaponType().toString());
        }
        human_statement.setBoolean(4, object.realHero());
        if (object.hasToothPick() == null) {
            human_statement.setNull(5, Types.BOOLEAN);
        } else {
            human_statement.setBoolean(5, object.hasToothPick());
        }
        if (object.impactSpeed() == null) {
            human_statement.setNull(6, Types.BIGINT);
        } else {
            human_statement.setLong(6, object.impactSpeed());
        }
        human_statement.setInt(7, id);
        ResultSet resultSet = human_statement.executeQuery();
        if (resultSet.next()) {
            coordinates_id = resultSet.getInt("coordinates_id");
            car_id = resultSet.getInt("car_id");
        } else {
            throw new NoSuchElementException();
        }
        String update_coordinates = "UPDATE coordinates SET x = ?, y = ? WHERE id = ?";
        PreparedStatement coordinates_statement = connection.prepareStatement(update_coordinates);
        coordinates_statement.setFloat(1, object.coordinates().getX());
        coordinates_statement.setLong(2, object.coordinates().getY());
        coordinates_statement.setInt(3, coordinates_id);
        coordinates_statement.executeUpdate();

        String update_car = "UPDATE car SET name = ?, cool = ? WHERE id = ?";
        PreparedStatement car_statement = connection.prepareStatement(update_car);
        car_statement.setString(1, object.car().getName());
        car_statement.setBoolean(2, object.car().isCool());
        car_statement.setInt(3, car_id);
        car_statement.executeUpdate();
    }

    public void remove(Integer id) throws SQLException {
        int coords_id = -1, car_id = -1;
        String queryHuman = "DELETE FROM human_being WHERE human_being.id = ? RETURNING coordinates_id, car_id";
        PreparedStatement humanStatement = connection.prepareStatement(queryHuman);
        humanStatement.setInt(1, id);
        ResultSet resultSet = humanStatement.executeQuery();
        if (resultSet.next()) {
            coords_id = resultSet.getInt("coordinates_id");
            car_id = resultSet.getInt("car_id");
        } else {
            throw new NoSuchElementException();
        }
        String queryCoordinates = "DELETE FROM coordinates WHERE coordinates.id = ?";
        PreparedStatement coordsStatement = connection.prepareStatement(queryCoordinates);
        coordsStatement.setInt(1, coords_id);
        coordsStatement.executeUpdate();
        String queryCar = "DELETE FROM car WHERE car.id = ?";
        PreparedStatement carStatement = connection.prepareStatement(queryCar);
        carStatement.setInt(1, car_id);
        carStatement.executeUpdate();
    }

    public boolean checkPermission(Integer id, String user) throws SQLException {
        String query = "SELECT CAST(COUNT(1) AS INTEGER) FROM human_being WHERE id = ? AND user_name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.setString(2, user);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getBoolean(1);
        }
        return false;
    }

    public Stack<HumanBeing> getCollection() throws SQLException {
        String query = "SELECT human_being.*, coordinates.x, coordinates.y, car.name AS car_name, car.cool FROM human_being JOIN coordinates ON human_being.coordinates_id = coordinates.id JOIN car ON human_being.car_id = car.id";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        Stack<HumanBeing> collection = new Stack<>();
        while (resultSet.next()) {
            var id = resultSet.getInt("id");
            var name = resultSet.getString("name");
            var creationDate = resultSet.getTimestamp("creationDate");
            var mood = resultSet.getString("mood") != null ? Mood.valueOf(resultSet.getString("mood")) : null;
            var weaponType = resultSet.getString("weapon_type") != null ? WeaponType.valueOf(resultSet.getString("weapon_type")) : null;
            var realHero = resultSet.getBoolean("realHero");
            Boolean hasToothPick = resultSet.getBoolean("hasToothPick");
            if (resultSet.wasNull()) hasToothPick = null;
            Long impactSpeed = resultSet.getLong("impactSpeed");
            if (resultSet.wasNull()) impactSpeed = null;
            var coordinates_x = resultSet.getFloat("x");
            var coordinates_y = resultSet.getLong("y");
            var car_name = resultSet.getString("car_name");
            var car_cool = resultSet.getBoolean("cool");
            var user_name = resultSet.getString("user_name");
            Coordinates coordinates = new Coordinates();
            coordinates.setX(coordinates_x);
            coordinates.setY(coordinates_y);
            Car car = new Car();
            car.setName(car_name);
            car.setCool(car_cool);
            HumanBeing object = new HumanBeing();
            object.setId(id);
            object.setName(name);
            object.setCreationDate(creationDate);
            object.setMood(mood);
            object.setWeaponType(weaponType);
            object.setRealHero(realHero);
            object.setHasToothpick(hasToothPick);
            object.setImpactSpeed(impactSpeed);
            object.setUser(user_name);
            object.setCoordinates(coordinates);
            object.setCar(car);
            collection.add(object);
        }
        return collection;
    }

    public void reorder() throws SQLException {
        String query = "SELECT * FROM human_being ORDER BY id ?";
        String getMin = "SELECT MIN(id) FROM human_being";
        String getFirst = "SELECT id FROM human_being";
        Statement get_statement = connection.createStatement();
        ResultSet get_result = get_statement.executeQuery(getFirst);
        get_result.absolute(1);
        Integer id = get_result.getInt(1);

    }

    public Connection getConnection() {
        return connection;
    }
}
