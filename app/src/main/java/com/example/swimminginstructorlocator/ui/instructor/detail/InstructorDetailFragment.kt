package com.example.swimminginstructorlocator.ui.instructor.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.adapter.CourseAdapter
import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.data.model.CourseDetail
import com.example.swimminginstructorlocator.data.model.InstructorDetail
import com.example.swimminginstructorlocator.data.repo.CourseRepo
import com.example.swimminginstructorlocator.data.repo.InstructorRepo
import com.example.swimminginstructorlocator.data.service.CourseService
import com.example.swimminginstructorlocator.data.service.InstructorService
import com.example.swimminginstructorlocator.databinding.FragmentInstructorDetailBinding
import com.example.swimminginstructorlocator.listener.OnImageClickListener
import com.example.swimminginstructorlocator.ui.course.detail.CourseDetailFragment
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import com.example.swimminginstructorlocator.utils.ext.addFragment
import com.example.swimminginstructorlocator.utils.ext.goBackFragment
import com.example.swimminginstructorlocator.utils.ext.loadImageWithUrl
import com.example.swimminginstructorlocator.utils.ext.notNull
import java.lang.Exception

class InstructorDetailFragment : BaseViewBindingFragment<FragmentInstructorDetailBinding>(),
    InstructorDetailContract.View, OnImageClickListener {

    private lateinit var instructorDetailPresenter: InstructorDetailPresenter
    private var progressDialog: SweetAlertDialog? = null

    private val courseAdapter: CourseAdapter by lazy {
        CourseAdapter(this)
    }

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInstructorDetailBinding {
        return FragmentInstructorDetailBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        instructorDetailPresenter = InstructorDetailPresenter(
            InstructorService.getInstance(InstructorRepo.getInstance()),
            CourseService.getInstance(CourseRepo.getInstance())
        )
        instructorDetailPresenter.setView(this)
        instructorDetail?.id?.let { instructorDetailPresenter.getListCourse(it) }
    }

    @SuppressLint("SetTextI18n")
    override fun initData() {
        binding.tvInstructorName.text = instructorDetail?.instructorName
        binding.tvInstructorAge.text = instructorDetail?.age.toString()
        binding.tvInstructorDescription.text = instructorDetail?.description
        binding.tvInstructorGraduate.text = instructorDetail?.graduate
        binding.tvInstructorCertification.text = instructorDetail?.certificate
        binding.tvInstructorExperience.text = instructorDetail?.yearExperiences.toString()
        binding.tvInstructorEmail.text = instructorDetail?.email
        binding.tvInstructorPhone.text = instructorDetail?.phone

        if (instructorDetail?.gender == 1) {
            binding.tvInstructorGender.text = "Male"
        } else {
            binding.tvInstructorGender.text = "Female"
        }

        instructorDetail?.image.notNull {
            instructorDetail?.image?.let { value -> binding.imgInstructor.loadImageWithUrl(value) }
        }

        binding.imgBackButton.setOnClickListener {
            goBackFragment()
        }
    }

    override fun onGetListCourse(listCourse: MutableList<Course>) {
        if (listCourse.size == 0) {
            binding.tvNoCourseFound.visibility = View.VISIBLE
            binding.rcvCourse.visibility = View.GONE
        } else {
            courseAdapter.setData(listCourse)
            binding.rcvCourse.adapter = courseAdapter
            binding.tvNoCourseFound.visibility = View.GONE
            binding.rcvCourse.visibility = View.VISIBLE
        }
    }

    override fun onGetCourseDetail(course: CourseDetail) {
        progressDialog?.dismissWithAnimation()

        CourseDetailFragment.setCourseDetail(course)
        val courseDetail = CourseDetailFragment.newInstance()
        addFragment(
            R.id.fragment_home_container,
            courseDetail,
            addToBackStack = true
        )
    }

    // ----------------------     On Item Click Listener     ----------------------

    override fun onImageClick(item: Any) {
        if (item is Course) {
            progressDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText(R.string.loading)
                .apply {
                    setCancelable(false)
                    show()
                }
            instructorDetailPresenter.getCourseDetail(item.id)
        }
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private var instructorDetail: InstructorDetail? = null

        @JvmStatic
        fun newInstance() = InstructorDetailFragment()

        @JvmStatic
        fun setInstructorDetail(instructorDetail: InstructorDetail) {
            this.instructorDetail = instructorDetail
        }
    }
}