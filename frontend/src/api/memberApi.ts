const BASE_URL = 'http://localhost:8080/api/members'

export interface MemberResponse {
  id: number
  name: string
  email: string
  createdAt: string
}

export interface SignupRequest {
  name: string
  email: string
  password: string
}

export interface LoginRequest {
  email: string
  password: string
}

export async function signup(data: SignupRequest): Promise<MemberResponse> {
  const res = await fetch(BASE_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  })
  if (!res.ok) {
    throw new Error('회원가입에 실패했습니다.')
  }
  return res.json()
}

export async function login(data: LoginRequest): Promise<MemberResponse> {
  const res = await fetch(`${BASE_URL}/login`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  })
  if (res.status === 401) {
    throw new Error('이메일 또는 비밀번호가 일치하지 않습니다.')
  }
  if (!res.ok) {
    throw new Error('로그인에 실패했습니다.')
  }
  return res.json()
}
