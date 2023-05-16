package com.mediscreen.ui.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * This is the proxy for the assessment api.
 */
@FeignClient(name = "assessment", url = "${assessmentUrl}")
public interface AssessmentProxy {

    @GetMapping("diabetes/{patId}")
    String getAssessment(@PathVariable String patId);
}
