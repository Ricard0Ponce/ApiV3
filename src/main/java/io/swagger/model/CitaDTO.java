package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Validated
@Data // Agrega los getters y setters
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-03T21:32:08.208259185Z[GMT]")
public class CitaDTO {

    @JsonProperty("id")
    private Long id; // ID De la cita

    @JsonProperty("NumTrabajador")
    private String NumTrabajador;

    @JsonProperty("fecha")
    private LocalDate fecha = null;

    @JsonProperty("hora")
    private LocalTime hora = null;

    @JsonProperty("motivoCita")
    private String motivoCita = null;

    @JsonProperty("discapacidad")
    private Boolean discapacidad = null;

    @JsonProperty("comunidadIndigena")
    private Boolean comunidadIndigena = null;

    @JsonProperty("migrante")
    private Boolean migrante = null;

    @JsonProperty("matriculaAlumno")
    private String matriculaAlumno;

    public CitaDTO id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     * 
     * @return id
     **/
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "")

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CitaDTO hora(LocalTime hora) {
        this.hora = hora;
        return this;
    }

    /**
     * Get hora
     * 
     * @return hora
     **/
    @Schema(description = "")

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public CitaDTO motivoCita(String motivoCita) {
        this.motivoCita = motivoCita;
        return this;
    }

    /**
     * Get motivoCita
     * 
     * @return motivoCita
     **/
    @Schema(description = "")

    public String getMotivoCita() {
        return motivoCita;
    }

    public void setMotivoCita(String motivoCita) {
        this.motivoCita = motivoCita;
    }

    public CitaDTO discapacidad(Boolean discapacidad) {
        this.discapacidad = discapacidad;
        return this;
    }

    /**
     * Get discapacidad
     * 
     * @return discapacidad
     **/
    @Schema(description = "")

    public Boolean isDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(Boolean discapacidad) {
        this.discapacidad = discapacidad;
    }

    public CitaDTO comunidadIndigena(Boolean comunidadIndigena) {
        this.comunidadIndigena = comunidadIndigena;
        return this;
    }

    /**
     * Get comunidadIndigena
     * 
     * @return comunidadIndigena
     **/
    @Schema(description = "")

    public Boolean isComunidadIndigena() {
        return comunidadIndigena;
    }

    public void setComunidadIndigena(Boolean comunidadIndigena) {
        this.comunidadIndigena = comunidadIndigena;
    }

    public CitaDTO migrante(Boolean migrante) {
        this.migrante = migrante;
        return this;
    }

    /**
     * Get migrante
     * 
     * @return migrante
     **/
    @Schema(description = "")

    public Boolean isMigrante() {
        return migrante;
    }

    public void setMigrante(Boolean migrante) {
        this.migrante = migrante;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        // String NumTrabajador matriculaAlumno
        CitaDTO citaDTO = (CitaDTO) o;
        return Objects.equals(this.id, citaDTO.id) &&
                Objects.equals(this.fecha, citaDTO.fecha) &&
                Objects.equals(this.hora, citaDTO.hora) &&
                Objects.equals(this.motivoCita, citaDTO.motivoCita) &&
                Objects.equals(this.discapacidad, citaDTO.discapacidad) &&
                Objects.equals(this.comunidadIndigena, citaDTO.comunidadIndigena) &&
                Objects.equals(this.NumTrabajador, citaDTO.NumTrabajador) &&
                Objects.equals(this.migrante, citaDTO.migrante) &&
                Objects.equals(this.matriculaAlumno, citaDTO.matriculaAlumno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, hora, motivoCita, discapacidad, comunidadIndigena,
                NumTrabajador,
                migrante, matriculaAlumno);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Cita {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    fecha: ").append(toIndentedString(fecha)).append("\n");
        sb.append("    hora: ").append(toIndentedString(hora)).append("\n");
        sb.append("    motivoCita: ").append(toIndentedString(motivoCita)).append("\n");
        sb.append("    discapacidad: ").append(toIndentedString(discapacidad)).append("\n");
        sb.append("    NumTrabajador: ").append(toIndentedString(NumTrabajador)).append("\n");
        sb.append("    comunidadIndigena: ").append(toIndentedString(comunidadIndigena)).append("\n");
        sb.append("    migrante: ").append(toIndentedString(migrante)).append("\n");
        sb.append("    matriculaAlumno: ").append(toIndentedString(matriculaAlumno)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
