package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Cita;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Psiquiatra
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-03T21:32:08.208259185Z[GMT]")


public class Psiquiatra   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("numTrabajador")
  private String numTrabajador = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("citas")
  @Valid
  private List<Cita> citas = null;

  @JsonProperty("nombres")
  private String nombres = null;

  @JsonProperty("apellidoPaterno")
  private String apellidoPaterno = null;

  @JsonProperty("apellidoMaterno")
  private String apellidoMaterno = null;

  public Psiquiatra id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Psiquiatra numTrabajador(String numTrabajador) {
    this.numTrabajador = numTrabajador;
    return this;
  }

  /**
   * Get numTrabajador
   * @return numTrabajador
   **/
  @Schema(description = "")
  
  @Size(min=10,max=10)   public String getNumTrabajador() {
    return numTrabajador;
  }

  public void setNumTrabajador(String numTrabajador) {
    this.numTrabajador = numTrabajador;
  }

  public Psiquiatra password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
   **/
  @Schema(description = "")
  
    public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Psiquiatra citas(List<Cita> citas) {
    this.citas = citas;
    return this;
  }

  public Psiquiatra addCitasItem(Cita citasItem) {
    if (this.citas == null) {
      this.citas = new ArrayList<Cita>();
    }
    this.citas.add(citasItem);
    return this;
  }

  /**
   * Get citas
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

  public Psiquiatra nombres(String nombres) {
    this.nombres = nombres;
    return this;
  }

  /**
   * Get nombres
   * @return nombres
   **/
  @Schema(description = "")
  
    public String getNombres() {
    return nombres;
  }

  public void setNombres(String nombres) {
    this.nombres = nombres;
  }

  public Psiquiatra apellidoPaterno(String apellidoPaterno) {
    this.apellidoPaterno = apellidoPaterno;
    return this;
  }

  /**
   * Get apellidoPaterno
   * @return apellidoPaterno
   **/
  @Schema(description = "")
  
    public String getApellidoPaterno() {
    return apellidoPaterno;
  }

  public void setApellidoPaterno(String apellidoPaterno) {
    this.apellidoPaterno = apellidoPaterno;
  }

  public Psiquiatra apellidoMaterno(String apellidoMaterno) {
    this.apellidoMaterno = apellidoMaterno;
    return this;
  }

  /**
   * Get apellidoMaterno
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
    Psiquiatra psiquiatra = (Psiquiatra) o;
    return Objects.equals(this.id, psiquiatra.id) &&
        Objects.equals(this.numTrabajador, psiquiatra.numTrabajador) &&
        Objects.equals(this.password, psiquiatra.password) &&
        Objects.equals(this.citas, psiquiatra.citas) &&
        Objects.equals(this.nombres, psiquiatra.nombres) &&
        Objects.equals(this.apellidoPaterno, psiquiatra.apellidoPaterno) &&
        Objects.equals(this.apellidoMaterno, psiquiatra.apellidoMaterno);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, numTrabajador, password, citas, nombres, apellidoPaterno, apellidoMaterno);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Psiquiatra {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    numTrabajador: ").append(toIndentedString(numTrabajador)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    citas: ").append(toIndentedString(citas)).append("\n");
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
