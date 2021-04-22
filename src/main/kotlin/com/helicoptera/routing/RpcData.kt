package com.helicoptera.routing

import com.helicoptera.data.db.model.Category
import com.helicoptera.data.db.model.User

interface RpcData

data class AuthorizationResponse(val user: User? = null, val error: String? = null) : RpcData

data class CategoriesResponse(val categories: List<Category>? = null, val error: String? = null) : RpcData

data class EmptyResponse(val error: String? = null) : RpcData