package com.arcsoft.hotel.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.arcsoft.hotel.pojo.CheckOut;
import com.arcsoft.hotel.pojo.CheckOutExample.Criteria;
import com.arcsoft.hotel.pojo.CheckOutExample.Criterion;
import com.arcsoft.hotel.pojo.CheckOutExample;

import java.util.List;
import java.util.Map;

public class CheckOutSqlProvider {

    public String countByExample(CheckOutExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("check_out");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(CheckOutExample example) {
        BEGIN();
        DELETE_FROM("check_out");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(CheckOut record) {
        BEGIN();
        INSERT_INTO("check_out");

        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }

        if (record.getRoomId() != null) {
            VALUES("room_id", "#{roomId,jdbcType=INTEGER}");
        }

        if (record.getPrice() != null) {
            VALUES("price", "#{price,jdbcType=DOUBLE}");
        }

        if (record.getCheckinDate() != null) {
            VALUES("checkin_date", "#{checkinDate,jdbcType=DATE}");
        }

        if (record.getCheckoutDate() != null) {
            VALUES("checkout_date", "#{checkoutDate,jdbcType=DATE}");
        }

        if (record.getTime() != null) {
            VALUES("time", "#{time,jdbcType=TIMESTAMP}");
        }

        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }

        if (record.getDocumentType() != null) {
            VALUES("document_type", "#{documentType,jdbcType=INTEGER}");
        }

        if (record.getDocumentNumber() != null) {
            VALUES("document_number", "#{documentNumber,jdbcType=VARCHAR}");
        }

        if (record.getCheckinId() != null) {
            VALUES("checkin_id", "#{checkinId,jdbcType=INTEGER}");
        }

        return SQL();
    }

    public String selectByExample(CheckOutExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("room_id");
        SELECT("price");
        SELECT("checkin_date");
        SELECT("checkout_date");
        SELECT("time");
        SELECT("name");
        SELECT("document_type");
        SELECT("document_number");
        SELECT("checkin_id");
        FROM("check_out");
        applyWhere(example, false);

        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }

        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        CheckOut record = (CheckOut) parameter.get("record");
        CheckOutExample example = (CheckOutExample) parameter.get("example");

        BEGIN();
        UPDATE("check_out");

        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=INTEGER}");
        }

        if (record.getRoomId() != null) {
            SET("room_id = #{record.roomId,jdbcType=INTEGER}");
        }

        if (record.getPrice() != null) {
            SET("price = #{record.price,jdbcType=DOUBLE}");
        }

        if (record.getCheckinDate() != null) {
            SET("checkin_date = #{record.checkinDate,jdbcType=DATE}");
        }

        if (record.getCheckoutDate() != null) {
            SET("checkout_date = #{record.checkoutDate,jdbcType=DATE}");
        }

        if (record.getTime() != null) {
            SET("time = #{record.time,jdbcType=TIMESTAMP}");
        }

        if (record.getName() != null) {
            SET("name = #{record.name,jdbcType=VARCHAR}");
        }

        if (record.getDocumentType() != null) {
            SET("document_type = #{record.documentType,jdbcType=INTEGER}");
        }

        if (record.getDocumentNumber() != null) {
            SET("document_number = #{record.documentNumber,jdbcType=VARCHAR}");
        }

        if (record.getCheckinId() != null) {
            SET("checkin_id = #{record.checkinId,jdbcType=INTEGER}");
        }

        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("check_out");

        SET("id = #{record.id,jdbcType=INTEGER}");
        SET("room_id = #{record.roomId,jdbcType=INTEGER}");
        SET("price = #{record.price,jdbcType=DOUBLE}");
        SET("checkin_date = #{record.checkinDate,jdbcType=DATE}");
        SET("checkout_date = #{record.checkoutDate,jdbcType=DATE}");
        SET("time = #{record.time,jdbcType=TIMESTAMP}");
        SET("name = #{record.name,jdbcType=VARCHAR}");
        SET("document_type = #{record.documentType,jdbcType=INTEGER}");
        SET("document_number = #{record.documentNumber,jdbcType=VARCHAR}");
        SET("checkin_id = #{record.checkinId,jdbcType=INTEGER}");

        CheckOutExample example = (CheckOutExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(CheckOut record) {
        BEGIN();
        UPDATE("check_out");

        if (record.getRoomId() != null) {
            SET("room_id = #{roomId,jdbcType=INTEGER}");
        }

        if (record.getPrice() != null) {
            SET("price = #{price,jdbcType=DOUBLE}");
        }

        if (record.getCheckinDate() != null) {
            SET("checkin_date = #{checkinDate,jdbcType=DATE}");
        }

        if (record.getCheckoutDate() != null) {
            SET("checkout_date = #{checkoutDate,jdbcType=DATE}");
        }

        if (record.getTime() != null) {
            SET("time = #{time,jdbcType=TIMESTAMP}");
        }

        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (record.getDocumentType() != null) {
            SET("document_type = #{documentType,jdbcType=INTEGER}");
        }

        if (record.getDocumentNumber() != null) {
            SET("document_number = #{documentNumber,jdbcType=VARCHAR}");
        }

        if (record.getCheckinId() != null) {
            SET("checkin_id = #{checkinId,jdbcType=INTEGER}");
        }

        WHERE("id = #{id,jdbcType=INTEGER}");

        return SQL();
    }

    protected void applyWhere(CheckOutExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }

        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }

        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }

                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }

                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }

        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}