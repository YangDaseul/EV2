package com.genesis.apps.room;

import androidx.room.Dao;
import androidx.room.Query;

import com.genesis.apps.comm.model.vo.developers.CarConnectVO;

import java.util.List;


@Dao
public abstract class CarConnectDao implements BaseDao<CarConnectVO> {
    @Query("SELECT * FROM CarConnectVO")
    public abstract List<CarConnectVO> selectAll();

    @Query("SELECT * FROM CarConnectVO WHERE vin=:vin")
    public abstract CarConnectVO select(String vin);

    @Query("DELETE from CarConnectVO")
    public abstract void deleteAll();

    @Query("DELETE FROM CarConnectVO WHERE vin =:vin")
    public abstract void deleteVin(String vin);

    @Query("UPDATE CarConnectVO SET result=:result WHERE carId=:carId")
    public abstract void updateResult(boolean result, String carId);

    @Query("UPDATE CarConnectVO SET carId=:carId WHERE vin=:vin")
    public abstract void updateCarId(String carId, String vin);

}