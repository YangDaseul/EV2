package com.genesis.apps.room;

import com.genesis.apps.comm.model.vo.CardVO;
import com.genesis.apps.comm.model.vo.FloatingMenuVO;
import com.genesis.apps.comm.model.vo.MenuVO;
import com.genesis.apps.comm.model.vo.NotiInfoVO;
import com.genesis.apps.comm.model.vo.QuickMenuVO;
import com.genesis.apps.comm.model.vo.UserVO;
import com.genesis.apps.comm.model.vo.VehicleVO;
import com.genesis.apps.comm.model.vo.WeatherVO;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {GlobalData.class, UserVO.class, MenuVO.class, WeatherVO.class, QuickMenuVO.class, FloatingMenuVO.class, VehicleVO.class, CardVO.class, NotiInfoVO.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GlobalDataDao globalDataDao();
    public abstract UserDao userDao();
    public abstract MenuDao menuDao();
    public abstract WeatherDao weatherDao();
    public abstract QuickMenuDao quickMenuDao();
    public abstract FloatingMenuDao floatingMenuDao();
    public abstract VehicleDao vehicleDao();
    public abstract CardDao cardDao();
    public abstract NotiInfoDao notiInfoDao();
}