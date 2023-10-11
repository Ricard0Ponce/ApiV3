package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AlumnoDTOLogin
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-03T21:32:08.208259185Z[GMT]")

public class AlumnoDTOLogin {

  @JsonProperty("matricula")
  private String matricula = null;

  @JsonProperty("nombres")
  private String nombres = null;

  @JsonProperty("apellidoPaterno")
  private String apellidoPaterno = null;

  @JsonProperty("apellidoMaterno")
  private String apellidoMaterno = null;

  public AlumnoDTOLogin matricula(String matricula) {
    this.matricula = matricula;
    return this;
  }

  /**
   * Get matricula
   * 
   * @return matricula
   **/
  @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "")

  @Size(min = 10, max = 10)
  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public AlumnoDTOLogin nombres(String nombres) {
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

  public AlumnoDTOLogin apellidoPaterno(String apellidoPaterno) {
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

  public AlumnoDTOLogin apellidoMaterno(String apellidoMaterno) {
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

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AlumnoDTOLogin alumnoDTOLogin = (AlumnoDTOLogin) o;
    return Objects.equals(this.matricula, alumnoDTOLogin.matricula) &&
        Objects.equals(this.nombres, alumnoDTOLogin.nombres) &&
        Objects.equals(this.apellidoPaterno, alumnoDTOLogin.apellidoPaterno) &&
        Objects.equals(this.apellidoMaterno, alumnoDTOLogin.apellidoMaterno);
  }

  @Override
  public int hashCode() {
    return Objects.hash(matricula, nombres, apellidoPaterno, apellidoMaterno);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AlumnoDTOLogin {\n");

    sb.append("    matricula: ").append(toIndentedString(matricula)).append("\n");
    sb.append("    nombres: ").append(toIndentedString(nombres)).append("\n");
    sb.append("    apellidoPaterno: ").append(toIndentedString(apellidoPaterno)).append("\n");
    sb.append("    apellidoMaterno: ").append(toIndentedString(apellidoMaterno)).append("\n");
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
