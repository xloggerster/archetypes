#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.entity;

import javax.annotation.Generated;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EntityPathBase;
import com.mysema.query.types.path.SimplePath;

/**
 * QAbstractPersistable is a Querydsl query type for AbstractPersistable
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QAbstractPersistable extends EntityPathBase<AbstractPersistable<? extends java.io.Serializable>> {

    private static final long serialVersionUID = 697085746;

    public final SimplePath<java.io.Serializable> id = createSimple("id", java.io.Serializable.class);

    public QAbstractPersistable(Path<? extends AbstractPersistable<? extends java.io.Serializable>> entity) {
        super(entity.getType(), entity.getMetadata());
    }

    @SuppressWarnings("unchecked")
    public QAbstractPersistable(PathMetadata<?> metadata) {
        super((Class)AbstractPersistable.class, metadata);
    }

}
