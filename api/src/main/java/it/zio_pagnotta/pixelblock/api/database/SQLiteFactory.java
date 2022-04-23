package it.zio_pagnotta.pixelblock.api.database;

import com.glyart.mystral.datasource.DataSourceFactory;
import com.glyart.mystral.exceptions.DataSourceInitException;
import org.jetbrains.annotations.NotNull;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import java.io.File;

public class SQLiteFactory implements DataSourceFactory<SQLiteDataSource> {
    private final String path;

    public SQLiteFactory(String databaseFilePath) {
        this.path = databaseFilePath + File.separator;
    }

    @Override
    @NotNull
    public SQLiteDataSource newDataSource() throws DataSourceInitException {
        SQLiteConfig config = new SQLiteConfig();
        config.setEncoding(SQLiteConfig.Encoding.UTF8);
        SQLiteDataSource dataSource = new SQLiteDataSource(config);
        dataSource.setUrl("jdbc:sqlite:" + path + "pixelblock.db");
        return dataSource;
    }
}