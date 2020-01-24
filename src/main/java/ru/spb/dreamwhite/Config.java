package ru.spb.dreamwhite;

import ru.spb.dreamwhite.repository.UserRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

  //  private static final File PROPS = new File("test/sql/user.properties");
      private static final File PROPS = new File( System.getProperty("TEST_ROOT") + "/sql/user.properties");
    private static final Config INSTANCE = new Config();

    private Properties props = new Properties();
    private File storageDir;
    private final UserRepository userRepository;

    private Config() {
      //  try (InputStream is = new FileInputStream(PROPS)) {
        //    props.load(is);
          //  userRepository = new UserRepository(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));

           // TCP-connection
        // userRepository = new UserRepository("jdbc:postgresql://dreamwhite.ru:5432/test_psql", "admin_psql", "Password2020#");
        userRepository = new UserRepository("jdbc:postgresql:test_psql?socketFactory=org.newsclub.net.unix.socketfactory.PostgresqlAFUNIXSocketFactory&socketFactoryArg=/var/run/postgresql/.s.PGSQL.5432", "admin_psql", "Password2020#");

     //   } catch (IOException e) {
      //      throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
      //  }

    }

    public static Config get() {
        return INSTANCE;
    }

    public File getStorageDir() {
        return storageDir;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
