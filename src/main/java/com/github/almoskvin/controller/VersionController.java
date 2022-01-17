package com.github.almoskvin.controller;

import com.github.almoskvin.utils.VersionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("api/v1/version")
@Slf4j
public class VersionController {

    @GetMapping("compare/{version1}/{version2}")
    @ResponseBody
    public int compareVersions(@PathVariable("version1") String version1, @PathVariable("version2") String version2) {
        log.info("New compareVersions request: version1 {}, version2 {}", version1, version2);
        try {
            String versionRegex = "(?!\\.)(\\d+(\\.\\d+)+)(?![\\d\\.])$";
            if (!version1.matches(versionRegex) || !version2.matches(versionRegex)) {
                log.error("Some or all versions ({}, {}) do not match required format", version1, version2);
                throw new IllegalArgumentException("Version format is wrong");
            }
            int comparisonResult = VersionUtils.compare(version1, version2);
            log.info("Calculated value {}", comparisonResult);
            return comparisonResult;
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

}
