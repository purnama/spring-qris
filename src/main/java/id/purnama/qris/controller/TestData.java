package id.purnama.qris.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class TestData {
    @NotNull
    private Integer id;

    @NotEmpty
    private String text;
}
