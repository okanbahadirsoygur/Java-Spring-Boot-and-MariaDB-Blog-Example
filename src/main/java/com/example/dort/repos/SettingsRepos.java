package com.example.dort.repos;

import com.example.dort.Entities.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface SettingsRepos extends JpaRepository<Settings, Long> {

    @Query("select s from Settings s where s.key = ?1")
    Settings getSettingsByKey(String key);

    /**
     * <p>
     * Update işlemini gerçekleştirebilmek için Modifying, Transactional annotations'ları tanımlanmak zorundadır.
     * Query türü nativeQuery olmalıdır.
     * </p>
     */
    @Modifying
    @Query(value = "update Settings s set s.value = ?2 where s.key = ?1", nativeQuery = true)
    void setSettingsByKey( String key, String value);
}
