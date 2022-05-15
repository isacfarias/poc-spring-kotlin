package com.poc.user.repositories.specification

import com.poc.user.model.User
import org.springframework.data.jpa.domain.Specification
import java.util.*
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class UserSpecification(
    @Transient
    private var userId: Optional<Long>, @Transient
    private var username: Optional<String>
) : Specification<User?> {

    override fun toPredicate(root: Root<User?>, query: CriteriaQuery<*>, criteriaBuilder: CriteriaBuilder): Predicate? {
        val predicates: MutableList<Predicate> = ArrayList()
        username.ifPresent { v: String ->
            predicates.add(
                criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("username")),
                    "%" + v.lowercase(Locale.getDefault()) + "%"
                )
            )
        }
        userId.ifPresent { v: Long? -> predicates.add(criteriaBuilder.equal(root.get<Any>("userId"), v)) }
        return criteriaBuilder.and(*predicates.toTypedArray())
    }

}