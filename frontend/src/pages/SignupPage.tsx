import { useState, type FormEvent } from 'react'
import { useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import { signup } from '../api/memberApi'
import './AuthForm.css'

function SignupPage() {
  const navigate = useNavigate()
  const [name, setName] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault()
    try {
      await signup({ name, email, password })
      toast.success('회원가입이 완료되었습니다.')
      navigate('/')
    } catch (err) {
      toast.error(err instanceof Error ? err.message : '회원가입에 실패했습니다.')
    }
  }

  return (
    <div className="auth-page">
      <form className="auth-form" onSubmit={handleSubmit}>
        <h1>회원가입</h1>
        <input
          type="text"
          placeholder="이름"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
        <input
          type="email"
          placeholder="이메일"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="비밀번호"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <button type="submit" className="primary">
          회원가입
        </button>
        <button type="button" className="secondary" onClick={() => navigate('/')}>
          로그인 화면으로
        </button>
      </form>
    </div>
  )
}

export default SignupPage
