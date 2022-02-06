package com.example.dort.repos;

import com.example.dort.Entities.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepos extends JpaRepository<Settings, Long> {

    @Query("select s from Settings s where s.key = ?1")
    Settings getSettingsByKey(String key);
}
