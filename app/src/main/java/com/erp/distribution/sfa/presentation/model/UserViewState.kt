/**
 *
 * Copyright 2020 David Odari
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *          http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 **/
package com.erp.distribution.sfa.presentation.model

import com.erp.distribution.sfa.data.source.entity_security.FUserEntity
import com.erp.distribution.sfa.domain.model.FDivision
import com.erp.distribution.sfa.domain.model.FSalesman
import com.erp.distribution.sfa.domain.model.FUser
import com.erp.distribution.sfa.domain.model.FWarehouse
import com.erp.distribution.sfa.domain.model.states.Error


data class UserViewState(
        val isComplete: Boolean =false,
        val error: Error? = null,
        val fUser: FUser? = null,
        val fDivision: FDivision? = null,
        val fSalesman: FSalesman? = null,
        val fWarehouse: FWarehouse? = null
)