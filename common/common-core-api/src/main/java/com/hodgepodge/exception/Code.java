package com.hodgepodge.exception;

/**
 * <p>
 * Title: Code
 * </p>
 * <p>
 * Description: 默认错误编码
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月13日
 * @since 1.8
 */
public interface Code {

    /**
     * 服务器异常
     */
    String ERROR = "error";
    /**
     * 数据库异常
     */
    String DB = "db";
    /**
     * 请求体格式不正确
     */
    String NOT_READABLE = "notReadable";
    /**
     * 响应体格式不正确
     */
    String NOT_WRITABLE = "notWritable";
    /**
     * 必须为null
     */
    String MUST_NULL = "mustNull";
    /**
     * 不能为null
     */
    String NOT_NULL = "notNull";
    /**
     * 字符或者集合长度大于0
     */
    String NOT_EMPTY = "notEmpty";
    /**
     * 取出空格后字符长度大于0
     */
    String NOT_BLANK = "notBlank";
    /**
     * 必须为true
     */
    String ASSERT_TRUE = "assertTrue";
    /**
     * 必须为false
     */
    String ASSERT_FALSE = "assertFalse";
    /**
     * 最小长度
     */
    String MIN_LENGTH = "minLength";
    /**
     * 最大长度
     */
    String MAX_LENGTH = "maxLength";
    /**
     * 最小值
     */
    String MIN = "min";
    /**
     * 最大值
     */
    String MAX = "max";
    /**
     * 长度
     */
    String LENGTH = "length";
    /**
     * 区间范围
     */
    String RANGE = "range";
    /**
     * 整数
     */
    String DIGITS = "digits";
    /**
     * 过去时间
     */
    String PAST = "past";
    /**
     * 未来时间
     */
    String FUTURE = "future";
    /**
     * 正则匹配
     */
    String PATTERN = "pattern";
    /**
     * 电子邮箱格式
     */
    String EMAIL = "email";
    /**
     * 是否存在
     */
    String EXIST = "exist";
    /**
     * 不存在
     */
    String NOT_EXIST = "notExist";
    /**
     * 是否可用
     */
    String AVAILABLE = "available";
    /**
     * 是否不可用
     */
    String UNAVAILABLE = "unavailable";
    /**
     * 是否唯一
     */
    String UNIQUENESS = "uniqueness";
    /**
     * 未认证
     */
    String UNAUTHORIZED = "unauthorized";
    /**
     * 禁止的
     */
    String FORBIDDEN = "forbidden";
    /**
     * 所有者
     */
    String OWNER = "owner";
    /**
     * 不允许
     */
    String NOT_ALLOWED = "notAllowed";

    /**
     * 不支持
     */
    String NOT_SUPPORT = "notSupport";

    /**
     * 超时
     */
    String TIMEOUT = "timeout";

    /**
     * 类型不匹配
     */
    String TYPE_MIS_MATCH = "typeMismatch";
}
