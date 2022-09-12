package com.lm.entity.valid;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lm.entity.valid.annotation.ValidPassword;
import lombok.RequiredArgsConstructor;
import org.passay.*;
import org.passay.spring.SpringMessageResolver;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * 验证密码注解的实现类 ValidPassword
 */
@RequiredArgsConstructor
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    private final SpringMessageResolver messageResolver; // 这个是国际化的内容

    @Override
    public void initialize(final ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext context) {
        // 为什么等null和"" ，还要返回true，原因很简单，让null的用 @NotBlank
        // 这个意思：你输入内容，我才给你校验，否则就不校验
        if(StringUtils.isEmpty(password)){
            return true;
        }

        PasswordValidator validator = new PasswordValidator(messageResolver, Arrays.asList(
                // 长度规则：8 - 30 位
                new LengthRule(4, 30),
                // 至少有一个大写字母
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                // 至少有一个小写字母
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                // 至少有一个数字
                new CharacterRule(EnglishCharacterData.Digit, 1),
                // 至少有一个特殊字符
                new CharacterRule(EnglishCharacterData.Special, 1),
                // 不允许连续 3 个字母，按字母表顺序
                // alphabetical is of the form 'abcde', numerical is '34567', qwery is 'asdfg'
                // the false parameter indicates that wrapped sequences are allowed; e.g. 'xyzabc'
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),//abcedf
                // 不允许 3 个连续数字
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false),//123456
                // 不允许 QWERTY 键盘上的三个连续相邻的按键所代表的字符
                new IllegalSequenceRule(EnglishSequenceData.USQwerty, 5, false),//qwe
                // 不允许包含空格
                new WhitespaceRule()));

        RuleResult validate = validator.validate(new PasswordData(password));
        if (validate.isValid()) {
            return true;
        }

        // 这里可以覆盖错误异常信息中的默认提示message
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(String.join(",", validator.getMessages(validate)))
                .addConstraintViolation();
        return false;
    }
}
