package com.hodgepodge.exception;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * Title: 条件判断异常返回辅助类
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月13日
 * @since 1.8
 */
public class ReturnOn {


    /**
     * <p>
     * Title: 判断两个对象不相等返回服务器异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param a       对象a
     * @param b       对象b
     * @param message 错误信息
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void notEquals(Object a, Object b, String message) {
        isFalse(Objects.equals(a, b), message);
    }

    /**
     * <p>
     * Title: 判断两个对象不相等返回服务器异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param a       对象a
     * @param b       对象b
     * @param builder 异常响应体构造器
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void notEquals(Object a, Object b, ReturnErrorBuilder<?> builder) {
        isFalse(Objects.equals(a, b), builder);
    }

    /**
     * <p>
     * Title: 判断两个对象不相等返回服务器异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param a       对象a
     * @param b       对象b
     * @param message 错误信息
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void equals(Object a, Object b, String message) {
        isTrue(Objects.equals(a, b), message);
    }

    /**
     * <p>
     * Title: 判断两个对象相等返回异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param a       对象a
     * @param b       对象b
     * @param builder 异常响应体构造器
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void equals(Object a, Object b, ReturnErrorBuilder<?> builder) {
        isTrue(Objects.equals(a, b), builder);
    }

    /**
     * <p>
     * Title: 判断对象不包含有效字符返回服务器异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param object  判断对象
     * @param message 错误信息
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void isBlank(String object, String message) {
        isFalse(StringUtils.hasText(object), message);
    }

    /**
     * <p>
     * Title: 判断对象不包含有效字符返回异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param object  判断对象
     * @param builder 异常响应体构造器
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void isBlank(String object, ReturnErrorBuilder<?> builder) {
        isFalse(StringUtils.hasText(object), builder);
    }

    /**
     * <p>
     * Title: 判断对象包含任何字符返回服务器异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param object  判断对象
     * @param message 错误信息
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void notBlank(String object, String message) {
        isTrue(StringUtils.hasText(object), message);
    }

    /**
     * <p>
     * Title: 判断对象包含任何字符返回异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param object  判断对象
     * @param builder 异常响应体构造器
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void notBlank(String object, ReturnErrorBuilder<?> builder) {
        isTrue(StringUtils.hasText(object), builder);
    }

    /**
     * <p>
     * Title: 判断对象为空返回服务器异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param object  判断对象
     * @param message 错误信息
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void isEmpty(Object object, String message) {
        isTrue(ObjectUtils.isEmpty(object), message);
    }

    /**
     * <p>
     * Title: 判断对象为空返回异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param object  判断对象
     * @param builder 异常响应体构造器
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void isEmpty(Object object, ReturnErrorBuilder<?> builder) {
        isTrue(ObjectUtils.isEmpty(object), builder);
    }

    /**
     * <p>
     * Title: 判断对象不为空返回服务器异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param object  判断对象
     * @param message 错误信息
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void notEmpty(Object object, String message) {
        isFalse(ObjectUtils.isEmpty(object), message);
    }

    /**
     * <p>
     * Title: 判断对象不为空返回异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param object  判断对象
     * @param builder 异常响应体构造器
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void notEmpty(Object object, ReturnErrorBuilder<?> builder) {
        isFalse(ObjectUtils.isEmpty(object), builder);
    }

    /**
     * <p>
     * Title: 判断集合为空返回服务器异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param collection 判断集合
     * @param message    错误信息
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void isEmpty(Collection<?> collection, String message) {
        isTrue(CollectionUtils.isEmpty(collection), message);
    }

    /**
     * <p>
     * Title: 判断集合为空返回异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param collection 判断集合
     * @param builder    异常响应体构造器
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void isEmpty(Collection<?> collection, ReturnErrorBuilder<?> builder) {
        isTrue(CollectionUtils.isEmpty(collection), builder);
    }

    /**
     * <p>
     * Title: 判断集合不为空返回服务器异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param collection 判断集合
     * @param message    错误信息
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void notEmpty(Collection<?> collection, String message) {
        isFalse(ObjectUtils.isEmpty(collection), message);
    }

    /**
     * <p>
     * Title: 判断集合不为空返回异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param collection 判断集合
     * @param builder    异常响应体构造器
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void notEmpty(Collection<?> collection, ReturnErrorBuilder<?> builder) {
        isFalse(CollectionUtils.isEmpty(collection), builder);
    }

    /**
     * <p>
     * Title: 判断映射为空返回服务器异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param map     判断映射
     * @param message 错误信息
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void isEmpty(Map<?, ?> map, String message) {
        isTrue(CollectionUtils.isEmpty(map), message);
    }

    /**
     * <p>
     * Title: 判断映射为空返回服务器异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param map     判断映射
     * @param builder 异常响应体构造器
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void isEmpty(Map<?, ?> map, ReturnErrorBuilder<?> builder) {
        isTrue(CollectionUtils.isEmpty(map), builder);
    }

    /**
     * <p>
     * Title: 判断映射不为空返回服务器异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param map     判断映射
     * @param message 错误信息
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void notEmpty(Map<?, ?> map, String message) {
        isFalse(ObjectUtils.isEmpty(map), message);
    }

    /**
     * <p>
     * Title: 判断映射不为空返回服务器异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param map     判断映射
     * @param builder 异常响应体构造器
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void notEmpty(Map<?, ?> map, ReturnErrorBuilder<?> builder) {
        isFalse(CollectionUtils.isEmpty(map), builder);
    }

    /**
     * <p>
     * Title: 判断对象为空返回服务器异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param object 判断对象
     * @param message 错误信息
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void isNull(Object object, String message) {
        isTrue(object == null, message);
    }

    /**
     * <p>
     * Title: 判断对象为空返回异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param object 判断对象
     * @param builder 异常响应体构造器
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void isNull(Object object, ReturnErrorBuilder<?> builder) {
        isTrue(object == null, builder);
    }

    /**
     * <p>
     * Title: 判断对象不为空返回服务器异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param object 判断对象
     * @param message 错误信息
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void notNull(Object object, String message) {
        isTrue(object != null, message);
    }

    /**
     * <p>
     * Title: 判断对象不为空返回异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param object 判断对象
     * @param builder 异常响应体构造器
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void notNull(Object object, ReturnErrorBuilder<?> builder) {
        isTrue(object != null, builder);
    }

   /**
    * <p>
    * Title: 判断表达式为假返回服务器异常响应体
    * </p>
    * <p>
    * Description:
    * </p>
    *
    * @param expression 表达式
    * @param message 错误信息
    * @author 刘小杰
    * @date 2022年01月13日
    * @since 1.8
    */
    public static void isFalse(boolean expression, String message) {
        isTrue(!expression, message);
    }

    /**
     * <p>
     * Title: 判断表达式为假返回异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param expression 表达式
     * @param builder 异常响应体构造器
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void isFalse(boolean expression, ReturnErrorBuilder<?> builder) {
        isTrue(!expression, builder);
    }

    /**
     * <p>
     * Title: 判断表达式为真返回服务器异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param expression 表达式
     * @param message 错误信息
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void isTrue(boolean expression, String message) {
        isTrue(expression, getServerError(message));
    }

    /**
     * <p>
     * Title: 判断表达式为真返回异常响应体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param expression  表达式
     * @param builder 异常响应体构造器
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static void isTrue(boolean expression, ReturnErrorBuilder<?> builder) {
        if (builder == null) {
            throw getServerError("异常响应体构造器不能为空").build();
        }
        if (expression) {
            throw builder.build();
        }
    }

    /**
     * <p>
     * Title: 获取服务器异常
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param message
     * @return com.hodgepodge.exception.ReturnServerErrorBuilder
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    private static ReturnServerErrorBuilder getServerError(String message) {
        return Return.server().error(message).code(Code.ERROR);
    }

}
