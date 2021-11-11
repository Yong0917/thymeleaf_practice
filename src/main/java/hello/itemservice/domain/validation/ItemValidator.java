package hello.itemservice.domain.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
    } //클래스를 지정해서 어떤 클래스인지 파악

    @Override
    public void validate(Object target, Errors errors) {        //target = item   errors = bindingResult 라고 생각
        Item item = (Item) target;

        if (!StringUtils.hasText(item.getItemName())) {
            errors.rejectValue("itemName", "required");  //자동적으로 required 가 부여된 상위레벨부터 찾음 (filed)
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            errors.rejectValue("price", "range", new Object[]{1000, 10000000}, null);  //Object에 값 넣어주고 range 가 부여된 상위레벨 부터 찾음 (filed)
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            errors.rejectValue("quantity", "max", new Object[]{9999}, null); //Object에 값 넣어주고 max 가 부여된 상위레벨 부터 찾음 (filed)
        }

        //특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null); //Object에 값 넣어주고 range 가 부여된 상위레벨 부터 찾음 (ObjectError)
            }
        }
    }
}
