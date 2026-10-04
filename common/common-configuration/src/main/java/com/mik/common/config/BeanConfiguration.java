package com.mik.common.config;

import com.mik.common.documents.excel.annotation.ExcelColumnBindingType;
import com.mik.common.documents.excel.binding.ExcelAnnotationBinding;
import com.mik.common.documents.excel.mapper.ExcelExportCellMapper;
import com.mik.common.documents.excel.model.enums.ExcelSectionRender;
import com.mik.common.documents.excel.processor.ExcelAnnotationProcessor;
import com.mik.common.documents.excel.render.ExcelRender;
import com.mik.common.event.aop.aspects.ServiceLoggingCommonAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Lazy(false)
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BeanConfiguration {

    @Bean
    @Lazy(false)
    public ServiceLoggingCommonAspect serviceLoggingCommonAspect() {
        return new ServiceLoggingCommonAspect();
    }


    @Bean
    @Lazy(false)
    public Map<Class<?>, ExcelExportCellMapper> excelExportCellMapperMap(List<ExcelExportCellMapper> listCellMapper) {
        return listCellMapper.stream().collect(Collectors.toMap(ExcelExportCellMapper::getTypeCellMapper,Function.identity()));
    }

    @Bean
    @Lazy(false)
    public Map<ExcelSectionRender, ExcelRender> excelRenderMap(List<ExcelRender> listRender) {
        return listRender.stream().collect(Collectors.toMap(ExcelRender::getType, Function.identity()));
    }

    @Bean
    @Lazy(false)
    public Map<Class<? extends Annotation>, ExcelAnnotationProcessor> excelAnnotationProcessorMap(List<ExcelAnnotationProcessor> excelAnnotationProcessors) {
        return excelAnnotationProcessors.stream().collect(Collectors.toMap(ExcelAnnotationProcessor::getAnnotationClass, Function.identity()));
    }

    @Bean
    @Lazy(false)
    public Map<ExcelColumnBindingType, ExcelAnnotationBinding> excelAnnotationBindingMap(List<ExcelAnnotationBinding> listAnnotation) {
        return listAnnotation.stream().collect(Collectors.toMap(ExcelAnnotationBinding::getBindingType,Function.identity()));
    }

    @Bean
    public AuditorAware<String> auditorProvider(AuditorAwareImpl auditorAwareImpl) {
        return auditorAwareImpl;
    }


}
