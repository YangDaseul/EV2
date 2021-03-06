package com.genesis.apps.room;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.genesis.apps.comm.model.vo.VehicleVO;

import java.util.List;


@Dao
public abstract class VehicleDao implements BaseDao<VehicleVO> {

    @Query("SELECT * FROM VehicleVO ORDER BY _id DESC")
    public abstract List<VehicleVO> selectAll();

    @Query("SELECT * FROM VehicleVO WHERE custGbCd=:custGbCd")
    public abstract List<VehicleVO> select(String custGbCd);

    @Query("SELECT * FROM VehicleVO WHERE vin=:vin")
    public abstract VehicleVO selectVin(String vin);

    @Query("SELECT * FROM VehicleVO WHERE mainVhclYn='Y'")
    public abstract VehicleVO selectMainVehicle();

    //소유차량 우선, 주 이용 차량이고 , 서버에서 준 리스트 순서대로 정렬
    @Query("SELECT * FROM VehicleVO WHERE custGbCd='OV' OR custGbCd='CV' ORDER BY custGbCd DESC, mainVhclYn DESC, _id ASC")
    public abstract List<VehicleVO> selectMyCarList();

    //소유차량 우선, 주 이용 차량이고 , 서버에서 준 리스트 순서대로 정렬
    @Query("SELECT * FROM VehicleVO WHERE (custGbCd='OV' OR custGbCd='CV') AND evCd='EV' ORDER BY custGbCd DESC, mainVhclYn DESC, _id ASC")
    public abstract List<VehicleVO> selectEVCarList();

    //소유차량 우선, 주 이용 차량이고 , 서버에서 준 리스트 순서대로 정렬
    @Query("SELECT * FROM VehicleVO WHERE custGbCd='OV' ORDER BY custGbCd DESC, mainVhclYn DESC, _id ASC")
    public abstract List<VehicleVO> selectInsightExpnList();

    @Query("DELETE from VehicleVO")
    public abstract void deleteAll();

    @Query("DELETE FROM VehicleVO WHERE custGbCd =:custGbCd")
    public abstract void deleteCustGbCd(String custGbCd);

    @Transaction
    public void insertAndDeleteInTransaction(List<VehicleVO> list, String custGbCd){
        deleteCustGbCd(custGbCd);
        insert(list);
    }

    @Transaction
    public void insertAndDeleteInTransaction(VehicleVO vehicleVO, String custGbCd){
        deleteCustGbCd(custGbCd);
        insert(vehicleVO);
    }
}