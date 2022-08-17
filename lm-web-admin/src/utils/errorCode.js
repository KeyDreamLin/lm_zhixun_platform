// 这里是这个与后端同步的异常状态码，可以改写msg的信息，这样后端返回的msg不喜欢就可以在这里重新改写
export default{
    "100100":{errorCode:"100100",msg:"用户输入有误！",field:""},
    "100101":{errorCode:"100101",msg:"NO_LOGIN",field:""},
    "100102":{errorCode:"100102",msg:"登录失败，账号不存在/密码错误！",field:"password"},
    "100103":{errorCode:"100103",msg:"登录失败，账户已被禁用！",field:""},
    "100104":{errorCode:"100104",msg:"token异常",field:""},
    "100105":{errorCode:"100105",msg:"token not found or due",field:""},
    "100106":{errorCode:"100106",msg:"用户不存在",field:""},
    "100107":{errorCode:"100107",msg:"用户已在其他地方登录",field:""},
    "100108":{errorCode:"100108",msg:"验证码错误",field:"code"},
    "default":{errorCode:"default",msg:"未知异常",field:"default"},
}

// USER_INPUT_NULL_ERROR(100100,"用户输入有误！"),
// USER_NO_LOGIN(100101,"NO_LOGIN"),
// USER_LOGIN_NO_EXIST(100102,"登录失败，账号不存在/密码错误！"),
// USER_LOGIN_ACCOUNT_STATE_STOP_USE(100103,"登录失败，账户已被禁用！"),
// USER_TOKEN_ERROR(100104,"token异常"),
// USER_TOKEN_NOT_FOUND(100105,"token not found or due"),//令牌没找到或者过期，就是jwt过期了
// USER_NULL_ERROR(100106, "用户不存在"),
// USER_LOGIN_SAME(100107,"用户已在其他地方登录"),
// USER_INPUT_CODE_ERROR(100108,"验证码错误");