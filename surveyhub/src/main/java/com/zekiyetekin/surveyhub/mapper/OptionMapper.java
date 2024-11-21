package com.zekiyetekin.surveyhub.mapper;

import com.zekiyetekin.surveyhub.dto.OptionDto;
import com.zekiyetekin.surveyhub.entity.Option;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OptionMapper {

    public OptionDto toDto(Option option){
        return OptionDto.builder()
                .id(option.getId())
                .content(option.getContent())
                .build();
    }

    public List<OptionDto> convertList(List<Option> optionList){
        List<OptionDto> optionDtoList = new ArrayList<>();

        for(Option option : optionList){
            optionDtoList.add(toDto(option));
        }
        return optionDtoList;
    }
}
