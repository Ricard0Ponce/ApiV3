package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Cita
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-03T21:32:08.208259185Z[GMT]")
@Entity // La clase se crea en una entidad
@Table(name = "Cita") // Asigna nombre a la tabla.
@Data // Crea Getters/Setter de la clase con Lombook
public class Cita {
  @Id
  @JsonProperty("id")
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera su propia llave
  @Column(name = "cit_id")
  private Long id; // ID De la cita

  @Column(name = "cit_fecha")
  @JsonProperty("fecha")
  private String fecha = null;

  @Column(name = "cit_hora")
  @JsonProperty("hora")
  private String hora = null;

  @Column(name = "cit_idpsiquiatra")
  @JsonProperty("idPsiquiatra")
  private Long idPsiquiatra = null;

  @ManyToOne
  @JoinColumn(name = "alu_id") // Nombre de la columna de la llave foránea en la tabla Cita
  @JsonIgnore
  private Alumno alumno;

  /*
   * @ManyToOne
   * 
   * @JsonProperty("idAlumno") // Potencialmente aqui va la relacion
   * private Integer idAlumno = null; // Se cambio a Integer
   */
  @Column(name = "cit_motivocita")
  @JsonProperty("motivoCita")
  private String motivoCita = null;

  @Column(name = "cit_discapacidad")
  @JsonProperty("discapacidad")
  private Boolean discapacidad = null;

  @Column(name = "cit_comunidadindigena")
  @JsonProperty("comunidadIndigena")
  private Boolean comunidadIndigena = null;

  @Column(name = "cit_migrante")
  @JsonProperty("migrante")
  private Boolean migrante = null;

  public Cita id(Long id) {
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

  public Cita fecha(String fecha) {
    this.fecha = fecha;
    return this;
  }

  /**
   * Get fecha
   * 
   * @return fecha
   **/
  @Schema(description = "")

  @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public Cita hora(String hora) {
    this.hora = hora;
    return this;
  }

  /**
   * Get hora
   * 
   * @return hora
   **/
  @Schema(description = "")

  public String getHora() {
    return hora;
  }

  public void setHora(String hora) {
    this.hora = hora;
  }

  public Cita idPsiquiatra(Long idPsiquiatra) {
    this.idPsiquiatra = idPsiquiatra;
    return this;
  }

  /**
   * Get idPsiquiatra
   * 
   * @return idPsiquiatra
   **/
  @Schema(description = "")

  public Long getIdPsiquiatra() {
    return idPsiquiatra;
  }

  public void setIdPsiquiatra(Long idPsiquiatra) {
    this.idPsiquiatra = idPsiquiatra;
  }

  public Cita Alumno(Alumno alumno) {
    this.alumno = alumno;
    return this;
  }

  /**
   * Get idAlumno
   * 
   * @return idAlumno
   **/
  @Schema(description = "")

  public Alumno getAlumno() {
    return alumno;
  }

  public void setAlumno(Alumno alumno) {
    this.alumno = alumno;
  }

  public Cita motivoCita(String motivoCita) {
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

  public Cita discapacidad(Boolean discapacidad) {
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

  public Cita comunidadIndigena(Boolean comunidadIndigena) {
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

  public Cita migrante(Boolean migrante) {
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
    Cita cita = (Cita) o;
    return Objects.equals(this.id, cita.id) &&
        Objects.equals(this.fecha, cita.fecha) &&
        Objects.equals(this.hora, cita.hora) &&
        Objects.equals(this.idPsiquiatra, cita.idPsiquiatra) &&
        Objects.equals(this.alumno, cita.alumno) &&
        Objects.equals(this.motivoCita, cita.motivoCita) &&
        Objects.equals(this.discapacidad, cita.discapacidad) &&
        Objects.equals(this.comunidadIndigena, cita.comunidadIndigena) &&
        Objects.equals(this.migrante, cita.migrante);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fecha, hora, idPsiquiatra, alumno, motivoCita, discapacidad, comunidadIndigena, migrante);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Cita {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    fecha: ").append(toIndentedString(fecha)).append("\n");
    sb.append("    hora: ").append(toIndentedString(hora)).append("\n");
    sb.append("    idPsiquiatra: ").append(toIndentedString(idPsiquiatra)).append("\n");
    sb.append("    alumno: ").append(toIndentedString(alumno)).append("\n");
    sb.append("    motivoCita: ").append(toIndentedString(motivoCita)).append("\n");
    sb.append("    discapacidad: ").append(toIndentedString(discapacidad)).append("\n");
    sb.append("    comunidadIndigena: ").append(toIndentedString(comunidadIndigena)).append("\n");
    sb.append("    migrante: ").append(toIndentedString(migrante)).append("\n");
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
