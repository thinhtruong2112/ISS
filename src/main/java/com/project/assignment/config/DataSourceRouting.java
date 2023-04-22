package com.project.assignment.config;

import com.project.assignment.constant.UserEnum;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DataSourceRouting extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return UserContextHolder.getBranchContext();
    }

    public void initDatasource(
        DataSource author1DataSource, DataSource author2DataSource,
        DataSource editor1DataSource, DataSource editor2DataSource,
        DataSource reviewer1DataSource, DataSource reviewer2DataSource,
        DataSource trackChair1DataSource, DataSource trackChair2DataSource,
        DataSource programChair1DataSource, DataSource programChair2DataSource) {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(UserEnum.AUTHOR_1, author1DataSource);
        dataSourceMap.put(UserEnum.AUTHOR_2, author2DataSource);
        dataSourceMap.put(UserEnum.REVIEWER_1, reviewer1DataSource);
        dataSourceMap.put(UserEnum.REVIEWER_2, reviewer2DataSource);
        dataSourceMap.put(UserEnum.EDITOR_1, editor1DataSource);
        dataSourceMap.put(UserEnum.EDITOR_2, editor2DataSource);
        dataSourceMap.put(UserEnum.TRACK_CHAIR_1, trackChair1DataSource);
        dataSourceMap.put(UserEnum.TRACK_CHAIR_2, trackChair2DataSource);
        dataSourceMap.put(UserEnum.PROGRAM_CHAIR_1, programChair1DataSource);
        dataSourceMap.put(UserEnum.PROGRAM_CHAIR_2, programChair2DataSource);
        this.setTargetDataSources(dataSourceMap);
        this.setDefaultTargetDataSource(author1DataSource);
    }
}
