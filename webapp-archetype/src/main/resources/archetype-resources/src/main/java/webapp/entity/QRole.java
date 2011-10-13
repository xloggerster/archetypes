#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.webapp.entity;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.BeanPath;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;

/**
 * QRole is a Querydsl query type for Role
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRole extends EntityPathBase<Role> {

	private static final long serialVersionUID = 1856413137;

	public static final QRole role = new QRole("role");

	public final QAbstractPersistable _super = new QAbstractPersistable(this);

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final StringPath name = createString("name");

	public QRole(String variable) {
		super(Role.class, forVariable(variable));
	}

	public QRole(BeanPath<? extends Role> entity) {
		super(entity.getType(), entity.getMetadata());
	}

	public QRole(PathMetadata<?> metadata) {
		super(Role.class, metadata);
	}

}
