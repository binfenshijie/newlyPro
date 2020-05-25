package com.newly.vas.business.utils;

/**
 * 人脸角度过滤参数
 */
public class FaceAngleParameter {

    public static String faceAngle(String yaw, String pitch, String roll) {
        yaw = yaw == null ? "," : yaw;
        pitch = pitch == null ? "," : pitch;
        roll = roll == null ? "," : roll;
        String[] yawArr = yaw.split(",");
        String[] pitchArr = pitch.split(",");
        String[] rollArr = roll.split(",");

        StringBuilder executeSql = new StringBuilder();
        executeSql.append(" AND (CASE WHEN (f.`PERSON_ID` = '') OR (f.`PERSON_ID` IS NULL) THEN f.`BLUR` >= ");
        executeSql.append(0.0);
        executeSql.append(" AND f.`BLUR` <= ");
        executeSql.append(0.2);
        executeSql.append(" AND f.`YAW` >= ");
        executeSql.append((yawArr.length == 2 && !yawArr[0].isEmpty()) ? Double.parseDouble(yawArr[0]) : -90.0);
        executeSql.append(" AND f.`YAW` <= ");
        executeSql.append((yawArr.length == 2 && !yawArr[1].isEmpty()) ? Double.parseDouble(yawArr[1]) : 90.0);

        executeSql.append(" AND f.`PITCH` >= ");
        executeSql.append((pitchArr.length == 2 && !pitchArr[0].isEmpty()) ? Double.parseDouble(pitchArr[0]) : -90.0);
        executeSql.append(" AND f.`PITCH` <= ");
        executeSql.append((pitchArr.length == 2 && !pitchArr[1].isEmpty()) ? Double.parseDouble(pitchArr[1]) : 90.0);

        executeSql.append(" AND f.`ROLL` >= ");
        executeSql.append((rollArr.length == 2 && !rollArr[0].isEmpty()) ? Double.parseDouble(rollArr[0]) : -180.0);
        executeSql.append(" AND f.`ROLL` <= ");
        executeSql.append((rollArr.length == 2 && !rollArr[1].isEmpty()) ? Double.parseDouble(rollArr[1]) : 180.0);
        executeSql.append(" AND f.`YAW` != 0 AND f.`PITCH` != 0 AND f.`ROLL` != 0 AND f.`FACE_WIDTH` >= 60 ELSE 1 = 1 END)");

        return executeSql.toString();
    }
}
