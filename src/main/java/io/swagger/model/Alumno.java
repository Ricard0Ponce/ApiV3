package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.model.Cita;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

/**
 * Alumno
 */
@Entity // La clase se crea en una entidad
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-03T21:32:08.208259185Z[GMT]")
@Table(name = "Alumno") // Asigna nombre a la tabla.
@Data // Crea Getters/Setter de la clase con Lombook
public class Alumno {
  @Id
  @Column(name = "alu_matricula")
  @JsonProperty("matricula")
  @NotBlank(message = "La matricula no puede estar en blanco.")
  private String matricula = null;

  @NotBlank(message = "El password no puede estar en blanco.")
  @Column(name = "alu_password")
  @JsonProperty("password")
  private String password = null;

  @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL)
  @JsonIgnore // Para evitar la serialización recursiva si usas Jackson
  private List<Cita> citas = new ArrayList<>();

  @NotBlank(message = "Los nombres no pueden estar en blanco.")
  @Column(name = "alu_nombres")
  @JsonProperty("nombres")
  private String nombres = null;

  @NotBlank(message = "El apellido no puede estar en blanco.")
  @Column(name = "alu_apellidopaterno")
  @JsonProperty("apellidoPaterno")
  private String apellidoPaterno = null;

  @NotBlank(message = "El apellido no puede estar en blanco.")
  @Column(name = "alu_apellidomaterno")
  @JsonProperty("apellidoMaterno")
  private String apellidoMaterno = null;

  @NotBlank(message = "El correo no puede estar en blanco.")
  @Column(name = "alu_email")
  @JsonProperty("email")
  private String email = null;

  @NotBlank(message = "El genero no puede estar en blanco.")
  @Column(name = "alu_genero")
  @JsonProperty("genero")
  private String genero = null;

  @NotBlank(message = "El telefono movil no puede estar en blanco.")
  @Column(name = "alu_telefonomovil")
  @JsonProperty("telefonoMovil")
  private String telefonoMovil = null;

  public Alumno matricula(String matricula) {
    this.matricula = matricula;
    return this;
  }

  /**
   * Get matricula
   * 
   * @return matricula
   **/
  @Schema(description = "")

  @Size(min = 10, max = 10)
  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public Alumno password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * 
   * @return password
   **/
  @Schema(description = "")

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Alumno citas(List<Cita> citas) {
    this.citas = citas;
    return this;
  }

  public Alumno addCitasItem(Cita citasItem) {
    if (this.citas == null) {
      this.citas = new ArrayList<Cita>();
    }
    this.citas.add(citasItem);
    return this;
  }

  /**
   * Get citas
   * 
   * @return citas
   **/
  @Schema(description = "")
  @Valid
  public List<Cita> getCitas() {
    return citas;
  }

  public void setCitas(List<Cita> citas) {
    this.citas = citas;
  }

  public Alumno nombres(String nombres) {
    this.nombres = nombres;
    return this;
  }

  /**
   * Get nombre
   * 
   * @return nombre
   **/
  @Schema(description = "")

  public String getNombres() {
    return nombres;
  }

  public void setNombres(String nombres) {
    this.nombres = nombres;
  }

  public Alumno apellidoPaterno(String apellidoPaterno) {
    this.apellidoPaterno = apellidoPaterno;
    return this;
  }

  /**
   * Get apellidoPaterno
   * 
   * @return apellidoPaterno
   **/
  @Schema(description = "")

  public String getApellidoPaterno() {
    return apellidoPaterno;
  }

  public void setApellidoPaterno(String apellidoPaterno) {
    this.apellidoPaterno = apellidoPaterno;
  }

  public Alumno apellidoMaterno(String apellidoMaterno) {
    this.apellidoMaterno = apellidoMaterno;
    return this;
  }

  /**
   * Get apellidoMaterno
   * 
   * @return apellidoMaterno
   **/
  @Schema(description = "")

  public String getApellidoMaterno() {
    return apellidoMaterno;
  }

  public void setApellidoMaterno(String apellidoMaterno) {
    this.apellidoMaterno = apellidoMaterno;
  }

  public Alumno email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * 
   * @return email
   **/
  @Schema(description = "")

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Alumno genero(String genero) {
    this.genero = genero;
    return this;
  }

  /**
   * Get genero
   * 
   * @return genero
   **/
  @Schema(description = "")

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public Alumno telefonoMovil(String telefonoMovil) {
    this.telefonoMovil = telefonoMovil;
    return this;
  }

  /**
   * Get telefonoMovil
   * 
   * @return telefonoMovil
   **/
  @Schema(description = "")

  public String getTelefonoMovil() {
    return telefonoMovil;
  }

  public void setTelefonoMovil(String telefonoMovil) {
    this.telefonoMovil = telefonoMovil;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Alumno alumno = (Alumno) o;
    return Objects.equals(this.matricula, alumno.matricula) &&
        Objects.equals(this.password, alumno.password) &&
        Objects.equals(this.citas, alumno.citas) &&
        Objects.equals(this.nombres, alumno.nombres) &&
        Objects.equals(this.apellidoPaterno, alumno.apellidoPaterno) &&
        Objects.equals(this.apellidoMaterno, alumno.apellidoMaterno) &&
        Objects.equals(this.email, alumno.email) &&
        Objects.equals(this.genero, alumno.genero) &&
        Objects.equals(this.telefonoMovil, alumno.telefonoMovil);
  }

  @Override
  public int hashCode() {
    return Objects.hash(matricula, password, citas, nombres, apellidoPaterno, apellidoMaterno, email, genero,
        telefonoMovil);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Alumno {\n");

    sb.append("    matricula: ").append(toIndentedString(matricula)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    citas: ").append(toIndentedString(citas)).append("\n");
    sb.append("    nombres: ").append(toIndentedString(nombres)).append("\n");
    sb.append("    apellidoPaterno: ").append(toIndentedString(apellidoPaterno)).append("\n");
    sb.append("    apellidoMaterno: ").append(toIndentedString(apellidoMaterno)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    genero: ").append(toIndentedString(genero)).append("\n");
    sb.append("    telefonoMovil: ").append(toIndentedString(telefonoMovil)).append("\n");
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
