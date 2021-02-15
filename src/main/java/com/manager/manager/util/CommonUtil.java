package com.manager.manager.util;

import com.manager.manager.common.SteelTypeConstants;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: mengwenyi
 * @Date: 2021/2/15 17:14
 */
@Component
public class CommonUtil {
    public static String getSteelTypeNameById(Integer id) {
        switch (id) {
            case SteelTypeConstants.LUOXUANGANGGUAN:
                return "螺旋钢管";
            case SteelTypeConstants.WUFENGANGGUAN:
                return "无缝管";
            case SteelTypeConstants.DUXINGUAN:
                return "镀锌管";
            case SteelTypeConstants.FANGGUAN:
                return "方管";
            case SteelTypeConstants.HANGUAN:
                return "焊管";
            case SteelTypeConstants.XINGCAI:
                return "锌管";
            case SteelTypeConstants.QITA:
                return "其他";
            default:
                return "";
        }
    }

}
