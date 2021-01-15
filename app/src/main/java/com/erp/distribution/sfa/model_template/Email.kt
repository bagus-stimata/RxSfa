package com.erp.distribution.sfa.model_template

/**
 * Julho, 03 2019
 *
 * @author suporte@moonjava.com.br (Tiago Aguiar).
 */
class Email {
    private var user: String? = null
    private var subject: String? = null
    private var preview: String? = null
    private var date: String? = null
    private var stared = false
    private var unread = false
    private var selected = false
    fun getUser(): String? {
        return user
    }

    fun setUser(user: String?) {
        this.user = user
    }

    fun getSubject(): String? {
        return subject
    }

    fun setSubject(subject: String?) {
        this.subject = subject
    }

    fun getPreview(): String? {
        return preview
    }

    fun setPreview(preview: String?) {
        this.preview = preview
    }

    fun getDate(): String? {
        return date
    }

    fun setDate(date: String?) {
        this.date = date
    }

    fun isStared(): Boolean {
        return stared
    }

    fun setStared(stared: Boolean) {
        this.stared = stared
    }

    fun isUnread(): Boolean {
        return unread
    }

    fun setUnread(unread: Boolean) {
        this.unread = unread
    }

    fun isSelected(): Boolean {
        return selected
    }

    fun setSelected(selected: Boolean) {
        this.selected = selected
    }

    class EmailBuilder private constructor() {
        var user: String? = null
            private set
        var subject: String? = null
            private set
        var preview: String? = null
            private set
        var date: String? = null
            private set
        var isStared = false
            private set
        var isUnread = false
            private set
        var isSelected = false
            private set

        fun setUser(user: String?): EmailBuilder {
            this.user = user
            return this
        }

        fun setSubject(subject: String?): EmailBuilder {
            this.subject = subject
            return this
        }

        fun setPreview(preview: String?): EmailBuilder {
            this.preview = preview
            return this
        }

        fun setDate(date: String?): EmailBuilder {
            this.date = date
            return this
        }

        fun setStared(stared: Boolean): EmailBuilder {
            isStared = stared
            return this
        }

        fun setUnread(unread: Boolean): EmailBuilder {
            isUnread = unread
            return this
        }

        fun setSelected(selected: Boolean): EmailBuilder {
            isSelected = selected
            return this
        }

        fun build(): Email {
            val email = Email()
            email.user = user
            email.subject = subject
            email.preview = preview
            email.date = date
            email.stared = isStared
            email.unread = isUnread
            email.selected = isSelected
            return email
        }

        companion object {
            fun builder(): EmailBuilder {
                return EmailBuilder()
            }
        }
    }
}