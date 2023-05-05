package com.spring.docon.entity;

import com.spring.docon.entity.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "provider")
public class ProviderEntity extends BaseEntity {

    @Id
    @SequenceGenerator(name = "provider_seq_id", sequenceName = "provider_seq_id", initialValue = 500, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "provider_seq_id")
    @Column(name = "provider_id")
    private Long providerId;

    @Column(name = "mci_registration_number")
    private String mciRegistrationNumber;

    @Column(name = "experience")
    private String experience;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserRegisterEntity user;
}

