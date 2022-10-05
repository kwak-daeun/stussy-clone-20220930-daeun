package com.stussy.stussyclone20220930kde.dto.vaildation;


import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({ValidationGroups.NotBlankGroup.class,
                ValidationGroups.SizeGroup.class,
                ValidationGroups.PatternCheckGroup.class,
                Default.class
})
public interface ValidationSequence {


}
