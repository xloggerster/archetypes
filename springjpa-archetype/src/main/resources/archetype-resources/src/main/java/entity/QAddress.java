#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QAddress is a Querydsl query type for Address
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAddress extends EntityPathBase<Address> {

    private static final long serialVersionUID = -2065239942;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QAddress address = new QAddress("address");

    public final org.springframework.data.jpa.domain.QAbstractPersistable _super = new org.springframework.data.jpa.domain.QAbstractPersistable(this);

    public final StringPath city = createString("city");

    public final StringPath country = createString("country");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath postalCode = createString("postalCode");

    public final StringPath street = createString("street");

    public final EnumPath<Address.Type> type = createEnum("type", Address.Type.class);

    public final QUser user;

    public QAddress(String variable) {
        this(Address.class, forVariable(variable), INITS);
    }

    public QAddress(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAddress(PathMetadata<?> metadata, PathInits inits) {
        this(Address.class, metadata, inits);
    }

    public QAddress(Class<? extends Address> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

