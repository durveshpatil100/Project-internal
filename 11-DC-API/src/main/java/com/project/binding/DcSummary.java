package com.project.binding;

import lombok.Data;

import java.util.List;

@Data
public class DcSummary {

    private Income income;

    private Education education;

    private List<Child> childs;

    private String planName;
}
