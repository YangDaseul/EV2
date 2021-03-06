package com.genesis.apps.comm.model.api.gra;

import com.genesis.apps.comm.model.BaseData;
import com.genesis.apps.comm.model.api.APIInfo;
import com.genesis.apps.comm.model.api.BaseRequest;
import com.genesis.apps.comm.model.api.BaseResponse;
import com.genesis.apps.comm.model.vo.QuickMenuVO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @file GRA_LGN_0006
 * @Brief 고객구분별 퀵메뉴
 * @author hjpark
 */
public class LGN_0006 extends BaseData {
    /**
     * @brief LGN_0006의 요청 항목
     * @author hjpark
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    static
    class Request extends BaseRequest {
        public Request(String menuId){
            setData(APIInfo.GRA_LGN_0006.getIfCd(), menuId);
        }
    }

    /**
     * @brief LGN_0006의 응답 항목
     * @author hjpark
     * @see #qckMenuList 퀵메뉴리스트
     */
    @EqualsAndHashCode(callSuper = true)
    public @Data
    class Response extends BaseResponse {
        @Expose
        @SerializedName("qckMenuList")
        private List<QuickMenuVO> qckMenuList;
    }
}
