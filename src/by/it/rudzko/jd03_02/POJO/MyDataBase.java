package by.it.rudzko.jd03_02.POJO;

import by.it.rudzko.jd03_02.CN;
import by.it.rudzko.jd03_02.CRUD.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.sql.*;
import java.util.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "myDataBase", propOrder = {

})
public class MyDataBase {

    @XmlElement(name = "Roles", required = true)
    protected Roles roles;
    @XmlElement(name = "Users", required = true)
    protected Users users;
    @XmlElement(name = "Readership", required = true)
    protected Readership readership;
    @XmlElement(name = "Periodicals", required = true)
    protected Periodicals periodicals;
    @XmlElement(name = "Subscription", required = true)
    private Subscription subscription;

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles value) {
        this.roles = value;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users value) {
        this.users = value;
    }

    public Readership getReadership() {
        return readership;
    }

    public void setReadership(Readership value) {
        this.readership = value;
    }

    public Periodicals getPeriodicals() {
        return periodicals;
    }

    public void setPeriodicals(Periodicals value) {
        this.periodicals = value;
    }

    private Subscription getSubscription() {
        return subscription;
    }

    void setSubscription(Subscription value) {
        this.subscription = value;
    }

    private void deleteTables() {

        try (Connection connection = CN.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    String.format(Locale.ENGLISH, "DROP TABLE IF EXISTS Subscription, Periodicals, Readership, Users, Roles")
            );
        } catch (SQLException e) {
            System.out.println("No connection. Can't remove tables from database.\n"+e.getMessage());
        }
        System.out.println("Tables removed.");
    }

    private void createTables() {
        try (Connection connection = CN.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    String.format(Locale.ENGLISH, "CREATE TABLE olgarudzko.Roles ( ID INT NULL AUTO_INCREMENT , Role VARCHAR(20) ," +
                            " PRIMARY KEY (ID)) ENGINE = InnoDB;")
            );
            statement.executeUpdate(
                    String.format(Locale.ENGLISH, "CREATE TABLE olgarudzko.Users ( ID INT NULL AUTO_INCREMENT , Name VARCHAR(30) " +
                            "NOT NULL , FK_Role INT NOT NULL , BirthYear INT(4) NOT NULL , Sex CHAR NOT NULL , " +
                            "PRIMARY KEY (ID), FOREIGN KEY (FK_Role) REFERENCES Roles(ID)) ENGINE = InnoDB;")
            );

            statement.executeUpdate(
                    String.format(Locale.ENGLISH, "CREATE TABLE olgarudzko.Readership ( ID INT NULL AUTO_INCREMENT , Audience VARCHAR(20)" +
                            " NOT NULL , PRIMARY KEY (ID)) ENGINE = InnoDB;")
            );
            statement.executeUpdate(
                    String.format(Locale.ENGLISH, "CREATE TABLE olgarudzko.Periodicals ( ID INT NULL AUTO_INCREMENT , Title VARCHAR(20)" +
                            " NOT NULL , SubIndex INT NOT NULL , FK_Readership INT NOT NULL , FK_Added INT " +
                            "NOT NULL , PRIMARY KEY (ID) , FOREIGN KEY (FK_Readership) REFERENCES Readership(ID) , " +
                            "FOREIGN KEY (FK_Added) REFERENCES Users(ID)) ENGINE = InnoDB;")
            );
            statement.executeUpdate(
                    String.format(Locale.ENGLISH, "CREATE TABLE olgarudzko.Subscription ( ID INT NULL AUTO_INCREMENT , FK_Subscriber INT " +
                            "NOT NULL , FK_Periodical INT NOT NULL , PRIMARY KEY (ID) , FOREIGN KEY (FK_Subscriber)" +
                            " REFERENCES Users(ID) , FOREIGN KEY (FK_Periodical) REFERENCES Periodicals(ID)) ENGINE = InnoDB;")
            );
        } catch (SQLException e) {
            System.out.println("No connection. Can't create tables in database.\n"+e.getMessage());
        }
        System.out.println("Tables created.");
    }

    private void fillTables(MyDataBase newDataBase) {
        RoleCRUD rc = new RoleCRUD();
        AudienceCRUD ac = new AudienceCRUD();
        UserCRUD uc = new UserCRUD();
        PeriodicalCRUD pc = new PeriodicalCRUD();
        SubscrCRUD sc = new SubscrCRUD();
        List<Role> roles = newDataBase.getRoles().getRoles();
        List<Audience> readership = newDataBase.getReadership().getReadership();
        List<User> users = newDataBase.getUsers().getUser();
        List<Periodical> periodicals = newDataBase.getPeriodicals().getPeriodicals();
        List<Subscr> subscription = newDataBase.getSubscription().getSubscr();
            for (Role r : roles) {
                rc.create(r);
            }
            for (Audience aud : readership) {
                ac.create(aud);
            }
            for (User us : users) {
                uc.create(us);
            }
            for (Periodical peri : periodicals) {
                pc.create(peri);
            }
            for (Subscr sub : subscription) {
                sc.create(sub);
            }
        System.out.println("Next database created:\n"+newDataBase.toString());
    }

    public MyDataBase buildDefaultStructure (){
        ObjectFactory now=new ObjectFactory();
        List<Role> listRole=new ArrayList<>();
        Role r1=now.createRole("Administrator"); listRole.add(r1);
        Role r2=now.createRole("Subscriber"); listRole.add(r2);
        Roles roles=now.createRoles(listRole);
        List<Audience> listAud=new ArrayList<>();
        Audience aud1=now.createAudience("Adults"); listAud.add(aud1);
        Audience aud2=now.createAudience("Men"); listAud.add(aud2);
        Audience aud3=now.createAudience("Women"); listAud.add(aud3);
        Audience aud4=now.createAudience("Teens"); listAud.add(aud4);
        Audience aud5=now.createAudience("Children"); listAud.add(aud5);
        Readership readership=now.createReadership(listAud);
        List <User> listUsers=new ArrayList<>();
        User user1=now.createUser("Ivan", r1, 1990, "M"); listUsers.add(user1);
        User user2=now.createUser("Mary", r2, 1991, "F"); listUsers.add(user2);
        User user3=now.createUser("Ruslan", r2, 1992, "M"); listUsers.add(user3);
        User user4=now.createUser("Ludmila", r2, 1993, "F"); listUsers.add(user4);
        Users users=now.createUsers(listUsers);
        List<Periodical> listPeri=new ArrayList<>();
        Periodical p1=now.createPeriodical("Дзеяслоў", 74813, aud1, user1); listPeri.add(p1);
        Periodical p2=now.createPeriodical("Маладосць", 74957, aud4, user1); listPeri.add(p2);
        Periodical p3=now.createPeriodical("Алеся", 74995, aud3, user1); listPeri.add(p3);
        Periodical p4=now.createPeriodical("Планета", 75036, aud1, user1); listPeri.add(p4);
        Periodical p5=now.createPeriodical("Женский Журнал", 74810, aud3, user1); listPeri.add(p5);
        Periodical p6=now.createPeriodical("Культура", 63875, aud1, user1); listPeri.add(p6);
        Periodical p7=now.createPeriodical("Минский курьер", 63395, aud1, user1); listPeri.add(p7);
        Periodical p8=now.createPeriodical("Прессбол", 63115, aud2, user1); listPeri.add(p8);
        Periodicals periodicals=now.createPeriodicals(listPeri);
        List <Subscr> listSub=new ArrayList<>();
        Subscr s1=now.createSubscr(user2, p1); listSub.add(s1);
        Subscr s2=now.createSubscr(user2, p5); listSub.add(s2);
        Subscr s3=now.createSubscr(user3, p8); listSub.add(s3);
        Subscr s4=now.createSubscr(user3, p7); listSub.add(s4);
        Subscr s5=now.createSubscr(user4, p3); listSub.add(s5);
        Subscription subscription=now.createSubscription(listSub);
        return now.createMyDataBase(users, roles,periodicals,readership, subscription);
    }

//    public MyDataBase getNewDataBase(String path) {
//        Unmarshaller unm;
//        MyDataBase newDataBase = this;
//        try {
//            unm = JAXBContext.newInstance(MyDataBase.class).createUnmarshaller();
//
//            FileReader fr = new FileReader(path);
//            System.out.println(unm.unmarshal(fr));
//            //newDataBase = (MyDataBase) unm.unmarshal(fr);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return newDataBase;
//    }

    public void reset(MyDataBase newDataBase) {
        deleteTables();
        createTables();
        fillTables(newDataBase);
    }

//    public void reset(String path) {
//        deleteTables();
//        createTables();
//        fillTables(getNewDataBase(path));
//    }

    public void printUsers(){
        try (Connection connection = CN.getConnection();
             Statement statement = connection.createStatement()) {
            Map<String, Integer> users=new HashMap<>();
            ResultSet usersSet = statement.executeQuery("select * from users;");
            while (usersSet.next()) {
                users.put(usersSet.getString("Name"), usersSet.getInt("FK_Role"));
            }
            Map<Integer, String> roles=new HashMap<>();
            ResultSet rolesSet = statement.executeQuery("select * from roles;");
            while (rolesSet.next()) {
                roles.put(rolesSet.getInt("ID"), rolesSet.getString("Role"));
            }
            for (Map.Entry<String, Integer> x : users.entrySet()) {
                if (roles.containsKey(x.getValue())) {
                    System.out.println(x.getKey() + " - " + roles.get(x.getValue()));
                }
            }
            System.out.println("There are "+users.size()+" users and "+roles.size()+" roles for them in database.");
        } catch (SQLException e) {
            System.out.println("No connection. Can't get users from database.\n"+e.getMessage());
        }
    }

    @Override
    public int hashCode() {
        return roles.hashCode()+users.hashCode()+periodicals.hashCode()+subscription.hashCode()+readership.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj instanceof MyDataBase) {
            MyDataBase mdb = (MyDataBase) obj;
            return (this.roles.equals(mdb.roles)&&this.users.equals(mdb.users)&&this.readership.equals(mdb.readership)&&
            this.periodicals.equals(mdb.periodicals)&&this.subscription.equals(mdb.subscription));
        } else return false;
    }

    @Override
    public String toString() {
        return "Table Roles: "+roles.toString()+"\nTable Users: "+users.toString()+"\nTable Readership: "+readership.toString()+
                "\nTable Periodicals: "+periodicals.toString()+"\nTable Subscription: "+subscription.toString();
    }
}
