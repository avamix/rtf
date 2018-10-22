package ru.sbrf.nhl.rtf.container.springboot.templates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sbrf.nhl.rtf.core.model.Template;

@Repository
public interface TemplateRepository extends JpaRepository<Template, String> {
}
