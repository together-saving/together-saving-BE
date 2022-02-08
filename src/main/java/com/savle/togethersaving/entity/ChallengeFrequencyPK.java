package com.savle.togethersaving.entity;

import com.savle.togethersaving.config.FrequencyConverter;
import lombok.*;

import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.io.Serializable;


@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ChallengeFrequencyPK implements Serializable {

    private Long challengeId;

    @Convert(converter = FrequencyConverter.class)
    private Frequency frequency;

}