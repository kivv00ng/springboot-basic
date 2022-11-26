package org.prgrms.kdt.voucher.controller;

import lombok.extern.slf4j.Slf4j;
import org.prgrms.kdt.exception.voucher.VoucherUserException;
import org.prgrms.kdt.exception.voucher.VoucherServerException;
import org.prgrms.kdt.util.ControllerResult;
import org.prgrms.kdt.voucher.domain.Voucher;
import org.prgrms.kdt.voucher.service.VoucherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
public class VoucherController {

    private final VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping("/")
    public String voucherAll(Model model) {
        List<Voucher> voucherList = voucherService.getAllVoucher();
        model.addAttribute("voucherList", voucherList);
        return "vouchers";
    }

    @GetMapping("/voucher/{voucherId}")
    public String findVoucher(@PathVariable("voucherId") Long voucherId, Model model) {
        Voucher findVoucher = voucherService.findById(voucherId);
        model.addAttribute("findVoucher", findVoucher);
        return "voucher";
    }

    @GetMapping("/voucher/insertForm")
    public String voucherForm() {
        return "insertForm";
    }

    @GetMapping("/voucher/updateForm/{voucherId}")
    public String updateForm(@PathVariable("voucherId") long voucherId, Model model) {
        Voucher voucher = voucherService.findById(voucherId);
        model.addAttribute("voucher", voucher);
        return "updateForm";
    }

    @PostMapping("/api/voucher/insert")
    public String insertVoucher(@RequestParam("typeNumber") String typeNumber, @RequestParam("discountDegree") long discountDegree) {
        voucherService.createVoucher(typeNumber, discountDegree);

        return "redirect:/";
    }

    @PostMapping("/api/voucher/delete/{voucherId}")
    public String deleteVoucherById(@PathVariable("voucherId") Long voucherId) {
        voucherService.deleteById(voucherId);

        return "redirect:/";
    }

    @PostMapping("/api/voucher/update/{voucherId}")
    public String updateVoucher(@PathVariable("voucherId") Long voucherId, @RequestParam("discountDegree") long discountDegree) {
        voucherService.updateVoucher(voucherId, discountDegree);
        return "redirect:/voucher/" + voucherId;
    }

    @ExceptionHandler()
    public void userHandler(VoucherUserException userException, HttpServletResponse response) throws IOException {
        log.error("[customerExceptionHandler] => {}", userException);

        response.sendError(ControllerResult.USER_ERROR);
    }


    @ExceptionHandler
    public void serverHandler(VoucherServerException serverException, HttpServletResponse response) throws IOException {
        log.error("[customerExceptionHandler] => {}", serverException);
        response.sendError(ControllerResult.SERVER_ERROR);
    }
}