#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ch.ralscha.extdirectspring.generator.Model;

@Entity
@Model(value = "E4ds.model.Role", readMethod = "userService.readRoles")
public class Role extends AbstractPersistable {

	@NotNull
	@Size(max = 50)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}