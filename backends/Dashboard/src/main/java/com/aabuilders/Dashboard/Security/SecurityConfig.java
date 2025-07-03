package com.aabuilders.Dashboard.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http    .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS configuration
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/expenses_form/get_form","/api/login","/api/paint/variant/save","/api/paint/variant/get/all","/api/paint/bulkUploadPaintVariants",
                                "/api/paint/variant/update/**","/api/paint/variant/delete/**","/api/paint/variant/delete/all","/api/paint_calculation/edit/paints/**","/api/user/all",
                                "/api/sign-in","/expenses_form/save","/expenses_form/update/**","/api/expenses/sites","/expenses_form/audit/**","/api/tile/tile/save",
                                "/api/tile/tile/all","/api/tile/tile/update/**","/googleUploader/pdfs","/api/tile/tile/updateIncrement","/api/tile/tile/increment","/api/user/delete/**"
                                ,"/api/tile/nameArea","/api/tile/bulkUpload","/api/tile/bulkUploadType","/api/tile/areaName","/api/tile-image/upload","/api/user/edit/**","/api/user/id/**",
                                "/api/tile/typeFloor/**","/api/tile-name-size/add","/api/tile-name-size/all","/api/tile/sizeBulkUpload","/api/tile/floorNameBulkUpload"
                                ,"/api/tiles/upload","/api/tile/size/quantity/**","/api/tiles/all/data","/api/tiles/**","/api/tiles/areaFloor","api/tile/floorType",
                                "/api/tile/nameFloor","/api/tile/floorName","api/tile/typeFloor","api/tile/size/quantity","api/tile/quantity/size","/api/tiles/change/**",
                                "/api/tile/nameArea/**","/api/tile/nameArea/all","/api/tile/size/quantity/all","/api/tile/nameFloor/**","/api/tile/nameFloor/all","/api/tile/nameFloorType/all",
                                "/api/tile/tile/delete/**","/api/tiles/tileNameBulkUpload","/api/tiles/allTile","/api/paints/upload","/api/paints/bulk-upload",
                                "/api/paints/all/data","/api/paints/change/**","/api/paints/delete/**","/api/paints/delete/allTile","/api/paint_calculation/save/paints",
                                "/api/paint_calculation/all/paints","/googleUploader/paintPdfs","/api/paint/pdf/updateIncrement","/api/paint/pdf/increment","/api/ceiling_coat/save"
                                ,"/api/ceiling_coat/getAll","/api/paint_type/save","/api/paint_type/getAll","/api/paint_type/edit/**","/api/paint_type/delete/**",
                                "/api/paint_type/deleteAll","/api/paint_type/bulkUpdate","/api/paintData/extra","/api/paintData/allExtra","/api/formulas/save","/api/formulas/getAll"
                                ,"/api/formulas/edit/**","/api/formulas/delete/all","/api/formulas/delete/**","/api/paint_type/bulk_upload","/api/formulas/bulk_upload"
                                ,"/api/paint_calculation/delete/paints/**","/api/rcc_formWork/save/form_work","/api/rcc_steel/save/steel","/api/rcc/upload/beamName"
                                ,"/api/rcc/all/beamNameData","/api/rcc/edit/**","/api/rcc/delete/**","/api/rcc/delete/allRccBeamNames","/api/paintDeduction/save"
                                ,"/api/paintDeduction/getAll","/api/paint-deductions/save","/api/paint-deductions/all","/api/rcc/formulas/save","/api/rcc/formulas/getAll"
                                ,"/api/rcc/formulas/edit/**","/api/rcc/formulas/delete/**","/api/rcc/formulas/delete/all","/api/rcc/formulas/bulk_upload"
                                ,"/api/beam_types/save","/api/beam_types/getAll","/api/beam_types/edit/**","/api/beam_types/delete/**","/api/beam_types/bulkUpload"
                                ,"/api/beam_types/delete/all","api/rcc/size/save","api/rcc/size/get","api/rcc/size/edit/**","api/rcc/size/delete/**","api/rcc/size/deleteAll"
                                ,"api/rcc/size/bulkUpload","/api/rcc_formWork/getAll","/api/rcc_concrete/save","/api/rcc_concrete/getAll","/api/tile/vendor/save"
                                ,"/api/tile/vendor/getAll","/api/tile/vendor/**","/api/tile/vendor/delete/**","/api/tile/vendor/delete/all","/api/tile/vendor/bulkUpload"
                                ,"/api/project_Names/getAll","/api/project_Names/save","/api/project_Names/deleteAll","/api/project_Names/bulk_upload","/api/bath_model/save"
                                ,"/api/bath_model/getAll","/api/bath_model/edit/**","/api/bath_model/delete/**","/api/bath_model/deleteAll","/api/bath_model/saveAll"
                                ,"/api/bath/item/save","/api/bath/getAll/item","/api/bath/edit/**","/api/bath/delete/**","/api/bath/deleteAll","/api/bath/item_name_bulkUpload"
                                ,"/api/brand_names/save","/api/brand_names/getAll","/api/brand_names/edit/**","/api/brand_names/delete/**","/api/brand_names/deleteAll"
                                ,"/api/brand_names/bulkUpload","/api/bath_types/save","/api/bath_types/getAll","/api/bath_types/edit/**","/api/bath_types/delete/**"
                                ,"/api/bath_types/deleteAll","/api/bath_types/bulkUpload","/api/bath_fixture_calculation/save","/api/bath_fixture_calculation/all"
                                ,"/googleUploader/bathPdfs","/api/bath_fixture_calculation/edit/**","/api/bath_fixture_calculation/delete/**","/api/bath_fixture_calculation/delete/all"
                                ,"/api/vendor_Names/save","/api/vendor_Names/getAll","/api/vendor_Names/bulk_upload","/api/vendor_Names/deleteAll","/api/contractor_Names/save","/api/contractor_Names/getAll"
                                ,"/api/contractor_Names/bulk_upload","/api/contractor_Names/deleteAll","/api/expenses_categories/save","/api/expenses_categories/getAll","/api/expenses_categories/bulk_upload"
                                ,"/api/expenses_categories/deleteAll","/expenses/googleUploader/uploadToGoogleDrive","/expenses_form/delete/**","/api/machine_tools/save"
                                ,"/api/machine_tools/getAll","/api/machine_tools/bulk_Upload","/api/machine_tools/deleteAll","/api/account_type/save","/api/account_type/getAll"
                                ,"/api/account_type/bulk_Upload","/api/account_type/deleteAll","/api/account_type/delete/**","/api/machine_tools/delete/**","/api/account_type/daily_checklist"
                                ,"/api/project_Names/edit/**","/api/project_Names/delete/**","/api/vendor_Names/delete/**","/api/vendor_Names/edit/**","/api/contractor_Names/delete/**"
                                ,"/api/contractor_Names/edit/**","/api/account_type/edit/**","/api/machine_tools/edit/**","/agreement/googleUploader/uploadToGoogleDrive","/api/user_roles/save"
                                ,"/api/user_roles/all","/api/user_roles/edit/**","/api/roles/save","/api/roles/all","/rentForm/googleUploader/uploadToGoogleDrive"

                        ).permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://dashboard.aabuilders.in"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList(
                "Authorization", "Content-Type", "Accept", "X-Requested-With", "Access-Control-Allow-Origin"
        ));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}