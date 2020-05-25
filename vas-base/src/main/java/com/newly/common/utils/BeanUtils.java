package com.newly.common.utils;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

/**
 * Created by bingo on 2020/4/19 下午4:12
 * bean拷贝工具，从一个bean拷贝到另外一个，
 * 字段名需一致
 */
public class BeanUtils {

    private BeanUtils() {
    }

    public static void copy(Object source, Object target) {
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
    }

    public static void copy(Object source, Object target, Converter converter) {
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), true);
        copier.copy(source, target, converter);
    }
}