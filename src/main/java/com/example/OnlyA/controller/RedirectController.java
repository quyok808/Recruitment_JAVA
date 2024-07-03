package com.example.OnlyA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RedirectController {

    @GetMapping("/viettel-group")
    public RedirectView redirectToViettelGroupJobs() {
        return new RedirectView("https://careers.viettel.com.vn/");
    }

    @GetMapping("/fpt-corporation")
    public RedirectView redirectToFPTCorporationJobs() {
        return new RedirectView("https://recruitment.fpt.com.vn/");
    }

    @GetMapping("/vingroup")
    public RedirectView redirectToVingroupJobs() {
        return new RedirectView("https://tuyendung.vingroup.net/");
    }

    @GetMapping("/bidv")
    public RedirectView redirectToBidvJobs() {
        return new RedirectView("https://www.bidv.com.vn/vi/tuyen-dung");
    }

    @GetMapping("/vietcombank")
    public RedirectView redirectToVietcombankJobs() {
        return new RedirectView("https://www.vietcombank.com.vn/tuyen-dung");
    }

    @GetMapping("/techcombank")
    public RedirectView redirectToTechcombankJobs() {
        return new RedirectView("https://www.techcombank.com.vn/tuyen-dung");
    }

    @GetMapping("/vietinbank")
    public RedirectView redirectToVietinbankJobs() {
        return new RedirectView("https://www.vietinbank.vn/web/home/vi/tuyen-dung");
    }

    @GetMapping("/mobifone")
    public RedirectView redirectToMobifoneJobs() {
        return new RedirectView("https://tuyendung.mobifone.vn/");
    }

    @GetMapping("/vnpt")
    public RedirectView redirectToVNPTJobs() {
        return new RedirectView("https://tuyendung.vnpt.vn/");
    }

    @GetMapping("/pv-gas")
    public RedirectView redirectToPVGasJobs() {
        return new RedirectView("https://www.pvgas.com.vn/vi/tuyen-dung");
    }

    @GetMapping("/saigon-coop")
    public RedirectView redirectToSaigonCoopJobs() {
        return new RedirectView("https://tuyendung.saigoncoop.com.vn/");
    }

    @GetMapping("/masan-group")
    public RedirectView redirectToMasanGroupJobs() {
        return new RedirectView("https://tuyendung.masangroup.com/");
    }

    @GetMapping("/baoviet-group")
    public RedirectView redirectToBaoVietGroupJobs() {
        return new RedirectView("https://www.baoviet.com.vn/vi/tuyen-dung");
    }

    @GetMapping("/sacombank")
    public RedirectView redirectToSacombankJobs() {
        return new RedirectView("https://www.sacombank.com.vn/wps/portal/tuyendung");
    }

    @GetMapping("/hdbank")
    public RedirectView redirectToHDBankJobs() {
        return new RedirectView("https://www.hdbank.com.vn/vi/tuyen-dung");
    }

    @GetMapping("/vietjet-air")
    public RedirectView redirectToVietjetAirJobs() {
        return new RedirectView("https://careers.vietjetair.com/");
    }

    @GetMapping("/bamboo-airways")
    public RedirectView redirectToBambooAirwaysJobs() {
        return new RedirectView("https://tuyendung.bambooairways.com/");
    }

    @GetMapping("/petrovietnam")
    public RedirectView redirectToPetroVietnamJobs() {
        return new RedirectView("https://tuyendung.pvn.vn/");
    }

    @GetMapping("/vinaphone")
    public RedirectView redirectToVinaphoneJobs() {
        return new RedirectView("https://www.vinaphone.com.vn/tuyen-dung");
    }

    @GetMapping("/vinfast")
    public RedirectView redirectToVinFastJobs() {
        return new RedirectView("https://tuyendung.vinfast.vn/");
    }

    @GetMapping("/th-true-milk")
    public RedirectView redirectToTHTrueMilkJobs() {
        return new RedirectView("https://www.thtruemilk.vn/tuyen-dung");
    }

    @GetMapping("/novaland-group")
    public RedirectView redirectToNovalandGroupJobs() {
        return new RedirectView("https://tuyendung.novaland.com.vn/");
    }

    @GetMapping("/sun-group")
    public RedirectView redirectToSunGroupJobs() {
        return new RedirectView("https://sunworld.vn/tuyen-dung/");
    }

    @GetMapping("/vietravel")
    public RedirectView redirectToVietravelJobs() {
        return new RedirectView("https://www.vietravel.com/tuyen-dung");
    }
}
