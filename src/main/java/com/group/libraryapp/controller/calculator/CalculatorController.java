package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //api 컨트롤러에 작성. 주어진 클래스를 API의 입구, 진입지점(Controller)로 등록한다.
public class CalculatorController {

    @GetMapping("/add") // 아래 함수를 HTTP Method가 GET이고 HTTP path가 /add인 API로 지정한다
    public int addTwoNumbers(CalculatorAddRequest request) { //주어진 쿼리를 함수 파라미터에 넣는다.
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply") // POST/multiply
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) {
        return request.getNumber1() * request.getNumber2();
    }
}
