package com.erp.distribution.sfa.security_model

object Role {
    /**
     * boleh tidak ditambahkan ROLE_
     * misal ADMIN menjadi ROLE_ADMIN
     * penambahan pada:
     * authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
     * oleh karena untuk menyamakan dengan konsep @Secure("ROLE_ADMIN")
     * vaadin maka sebaiknya ditambahkan
     */
    const val USER = "ROLE_USER"
    const val ADMIN = "ROLE_ADMIN"
    const val ACCOUNTING = "ROLE_ACCOUNTING"
    const val GUEST = "ROLE_GUEST" //as default

    //Menu digunakan suatu penanda MNU
    const val MNU_ADMIN_1 = "ROLE_MNU_ADMIN_1"
    const val MNU_PROFILE_1 = "ROLE_MNU_PROFILE_1"
    const val MNU_PERSON_1 = "ROLE_MNU_PERSON_1"

    // return new String[] { USER, ADMIN, MNU_ADMIN_1 };
    val allRoles: Array<String?>
        get() {
            // return new String[] { USER, ADMIN, MNU_ADMIN_1 };
            val arrLenght = authRoles.size + menuRoles.size
            val allRoles = arrayOfNulls<String>(arrLenght)
            var i = 0
            for (role in authRoles) {
                allRoles[i] = role
                i++
            }
            for (role in menuRoles) {
                allRoles[i] = role
                i++
            }
            return allRoles
        }
    val authRoles: Array<String>
        get() = arrayOf(USER, ADMIN, ACCOUNTING, GUEST)
    val menuRoles: Array<String>
        get() = arrayOf(MNU_ADMIN_1, MNU_PROFILE_1, MNU_PERSON_1)
}