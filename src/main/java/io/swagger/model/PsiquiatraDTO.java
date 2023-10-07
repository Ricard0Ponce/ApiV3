package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PsiquiatraDTO
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-03T21:32:08.208259185Z[GMT]")

public class PsiquiatraDTO {

  @JsonProperty("numTrabajador")
  private String numTrabajador = null;

  @JsonProperty("nombres")
  private String nombres = null;

  @JsonProperty("apellidoPaterno")
  private String apellidoPaterno = null;

  @JsonProperty("apellidoMaterno")
  private String apellidoMaterno = null;

  public PsiquiatraDTO numTrabajador(String numTrabajador) {
    this.numTrabajador = numTrabajador;
    return this;
  }

  /**
   * Get numTrabajador
   * 
   * @return numTrabajador
   **/
  @Schema(description = "")

  @Size(min = 10, max = 10)
  public String getNumTrabajador() {
    return numTrabajador;
  }

  public void setNumTrabajador(String numTrabajador) {
    this.numTrabajador = numTrabajador;
  }

  public PsiquiatraDTO nombres(String nombres) {
    this.nombres = nombres;
    return this;
  }

  /**
   * Get nombres
   * 
   * @return nombres
   **/
  @Schema(description = "")

  public String getNombres() {
    return nombres;
  }

  public void setNombres(String nombres) {
    this.nombres = nombres;
  }

  public PsiquiatraDTO apellidoPaterno(String apellidoPaterno) {
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

  public PsiquiatraDTO apellidoMaterno(String apellidoMaterno) {
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
    PsiquiatraDTO psiquiatraDTO = (PsiquiatraDTO) o;
    return Objects.equals(this.numTrabajador, psiquiatraDTO.numTrabajador) &&
        Objects.equals(this.nombres, psiquiatraDTO.nombres) &&
        Objects.equals(this.apellidoPaterno, psiquiatraDTO.apellidoPaterno) &&
        Objects.equals(this.apellidoMaterno, psiquiatraDTO.apellidoMaterno);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numTrabajador, nombres, apellidoPaterno, apellidoMaterno);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PsiquiatraDTO {\n");

    sb.append("    numTrabajador: ").append(toIndentedString(numTrabajador)).append("\n");
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
