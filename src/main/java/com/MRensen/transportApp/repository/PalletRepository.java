package com.MRensen.transportApp.repository;

import com.MRensen.transportApp.utils.Pallet.Pallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalletRepository extends JpaRepository<Pallet, Long> {
}
