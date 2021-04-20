package com.helicoptera.routing

import com.helicoptera.data.db.model.User

interface RpcData

data class AuthorizationResponse(val user: User? = null, val error: String? = null) : RpcData